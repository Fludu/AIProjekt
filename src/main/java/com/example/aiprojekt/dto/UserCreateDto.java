package com.example.aiprojekt.dto;

import com.example.aiprojekt.utils.EmailValidator.UserEmail;
import com.example.aiprojekt.utils.EmailValidator.UserLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserCreateDto(

        @Size(max = 255)
        @Email(message = "user.mail.notValid")
        @NotBlank(message = "user.mail.isBlank")
        @UserEmail
        String email,

        @Size(max = 24, message = "user.login.tooMuchCharacters")
        @NotBlank(message = "user.login.isBlank")
        @UserLogin
        String login,

        @NotBlank(message = "user.password.isBlank")
        String password) {
}
