package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.User;

public record UserReadDto(String login, String email) {

    public static UserReadDto of(User user) {
        return new UserReadDto(user.getLogin(), user.getEmail());
    }
}
