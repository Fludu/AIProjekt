package com.example.aiprojekt.controller;

import com.example.aiprojekt.dto.UserCreateDto;
import com.example.aiprojekt.dto.UserReadDto;
import com.example.aiprojekt.security.LoginCredentials;
import com.example.aiprojekt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserReadDto> register(
            @Valid @RequestBody UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.createUser(userCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }
}
