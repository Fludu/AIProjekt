package com.example.aiprojekt.Exception;

import javax.validation.constraints.NotBlank;

public class NameIsBussyException extends Throwable {
    public NameIsBussyException(@NotBlank String name) {
    }
}
