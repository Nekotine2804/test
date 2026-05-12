package com.demo.userservice.domain.vo;

public record Username(String value) {

    public Username {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Username invalid"
            );
        }
    }
}