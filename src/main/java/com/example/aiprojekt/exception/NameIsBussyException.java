package com.example.aiprojekt.exception;

import javax.validation.constraints.NotBlank;

public class NameIsBussyException extends Throwable {
    public NameIsBussyException(@NotBlank String name) {
    }
}
