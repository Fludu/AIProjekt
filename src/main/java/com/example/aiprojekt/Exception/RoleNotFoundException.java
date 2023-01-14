package com.example.aiprojekt.Exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String name) {
        super("Could not find role with name " + name);
    }

}