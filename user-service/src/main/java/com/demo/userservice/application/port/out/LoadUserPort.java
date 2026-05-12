package com.demo.userservice.application.port.out;

import com.demo.userservice.domain.model.User;

import java.util.Optional;

public interface LoadUserPort {

    Optional<User> findByUsername(
            String username
    );
}