package com.demo.userservice.application.service;

import com.demo.common.JwtProvider;
import com.demo.userservice.application.port.in.LoginCommand;
import com.demo.userservice.application.port.in.LoginUseCase;
import com.demo.userservice.application.port.out.LoadUserPort;
import com.demo.userservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService
        implements LoginUseCase {

    private final LoadUserPort loadUserPort;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Override
    public String login(
            LoginCommand command
    ) {

        User user =
                loadUserPort.findByUsername(
                        command.username()
                ).orElseThrow();

        boolean matches =
                passwordEncoder.matches(
                        command.password(),
                        user.getPassword().value()
                );

        if (!matches) {
            throw new RuntimeException(
                    "Bad credentials"
            );
        }

        return jwtProvider.generateToken(
                user.getUsername().value()
        );
    }
}