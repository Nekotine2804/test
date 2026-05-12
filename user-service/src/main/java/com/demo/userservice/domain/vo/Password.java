package com.demo.userservice.domain.vo;

public record Password(String value) {

    public Password {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be blank"
            );
        }

        if (value.length() < 6) {
            throw new IllegalArgumentException(
                    "Password must be at least 6 characters"
            );
        }
    }
}