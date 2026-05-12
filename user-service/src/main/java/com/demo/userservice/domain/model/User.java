package com.demo.userservice.domain.model;

import com.demo.userservice.domain.vo.Password;
import com.demo.userservice.domain.vo.Username;

public class User {

      private Long id;

      private Username username;

      private Password password;

      public User(
              Long id,
              Username username,
              Password password
      ) {

            this.id = id;
            this.username = username;
            this.password = password;
      }

      public Long getId() {
            return id;
      }

      public Username getUsername() {
            return username;
      }

      public Password getPassword() {
            return password;
      }
}