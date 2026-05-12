package com.demo.userservice.application.port.out;

import com.demo.userservice.domain.model.User;

public interface SaveUserPort {

    User save(User user);
}