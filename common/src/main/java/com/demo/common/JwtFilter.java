package com.demo.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Value("${USER_CLIENT_ID}")
    private String clientIdConfig;

    @Value("${USER_SECRET_ID}")
    private String secretIdConfig;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String clientId = request.getHeader("Client-Id");
        String secretId = request.getHeader("Client-Secret");
        boolean invalidClient = clientId == null || !clientId.equals(clientIdConfig);
        boolean invalidSecret = secretId == null || !secretId.equals(secretIdConfig);
        if (invalidClient || invalidSecret) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            return;
        }
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            return;
        }
        String token = authHeader.substring(7);
        boolean validToken = jwtProvider.validateToken(token);
        if (!validToken) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            return;
        }

        String subject = jwtProvider.getClientIdFromToken(token);
        boolean invalidJwtClient = subject == null || !subject.equals(clientId);

        if (invalidJwtClient) {
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            return;
        }
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        subject,
                        null,
                        new ArrayList<>()
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
}