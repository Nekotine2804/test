package com.demo.userservice.adapter.out.persistence;

import com.demo.userservice.adapter.out.persistence.entity.UserEntity;
import com.demo.userservice.adapter.out.persistence.repository.UserRepository;
import com.demo.userservice.application.port.out.SaveUserPort;
import com.demo.userservice.domain.model.User;
import com.demo.userservice.domain.vo.Password;
import com.demo.userservice.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveUserPersistenceAdapter
        implements SaveUserPort {

    private final UserRepository repository;

    @Override
    public User save(
            User user
    ) {

        UserEntity entity =
                new UserEntity();

        entity.setUsername(
                user.getUsername().value()
        );

        entity.setPassword(
                user.getPassword().value()
        );

        UserEntity saved =
                repository.save(entity);

        return new User(
                saved.getId(),
                new Username(saved.getUsername()),
                new Password(saved.getPassword())
        );
    }
}