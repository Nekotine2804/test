package com.demo.userservice.application.service;

import com.demo.userservice.application.port.in.RegisterCommand;
import com.demo.userservice.application.port.in.RegisterUseCase;
import com.demo.userservice.application.port.out.LoadUserPort;
import com.demo.userservice.application.port.out.SaveUserPort;
import com.demo.userservice.domain.model.User;
import com.demo.userservice.domain.vo.Password;
import com.demo.userservice.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterCommand command) {
        boolean existed =
                loadUserPort.findByUsername(command.username()).isPresent();

        if (existed) {
            throw new IllegalArgumentException("Username already exists");
        }

        String encodedPassword =
                passwordEncoder.encode(command.password());

        User user = new User(
                null,
                new Username(command.username()),
                new Password(encodedPassword)
        );

        saveUserPort.save(user);
    }
}
