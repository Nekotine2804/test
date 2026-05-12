package com.demo.userservice.application.port.in;

public record LoginCommand(
        String username,
        String password
) {
}