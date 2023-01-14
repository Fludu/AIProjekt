package com.example.aiprojekt.dto;

import com.example.aiprojekt.models.Role;
import lombok.Getter;

@Getter
public class RoleRequest {
    String name;

    public static Role of(RoleRequest roleRequest) {
        return new Role(roleRequest.getName());
    }
}
