package com.demo.userservice.adapter.in.web;

import com.demo.userservice.application.port.in.LoginCommand;
import com.demo.userservice.application.port.in.LoginUseCase;
import com.demo.userservice.application.port.in.RegisterCommand;
import com.demo.userservice.application.port.in.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterCommand command
    ) {

        registerUseCase.register(command);

        return "SUCCESS";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginCommand command
    ) {

        return loginUseCase.login(command);
    }
}