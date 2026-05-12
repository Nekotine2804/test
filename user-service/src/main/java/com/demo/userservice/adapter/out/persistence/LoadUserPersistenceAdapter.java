package com.demo.userservice.adapter.out.persistence;

import com.demo.userservice.adapter.out.persistence.entity.UserEntity;
import com.demo.userservice.adapter.out.persistence.repository.UserRepository;
import com.demo.userservice.application.port.out.LoadUserPort;
import com.demo.userservice.domain.model.User;
import com.demo.userservice.domain.vo.Password;
import com.demo.userservice.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoadUserPersistenceAdapter
        implements LoadUserPort {

    private final UserRepository repository;

    @Override
    public Optional<User> findByUsername(
            String username
    ) {

        Optional<UserEntity> optionalEntity =
                repository.findByUsername(username);

        if (optionalEntity.isEmpty()) {
            return Optional.empty();
        }

        UserEntity entity =
                optionalEntity.get();

        User user = new User(
                entity.getId(),
                new Username(
                        entity.getUsername()
                ),
                new Password(
                        entity.getPassword()
                )
        );

        return Optional.of(user);
    }
}