package com.demo.userservice.adapter.out.persistence.repository;

import com.demo.userservice.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(
            String username
    );
}
