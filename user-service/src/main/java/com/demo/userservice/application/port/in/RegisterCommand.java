package com.demo.userservice.application.port.in;

public record RegisterCommand(
        String username,
        String password
) {
}