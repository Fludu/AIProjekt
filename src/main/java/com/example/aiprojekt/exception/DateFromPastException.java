package com.example.aiprojekt.exception;

import java.time.LocalDateTime;

public class DateFromPastException extends RuntimeException {
    public DateFromPastException(LocalDateTime time) {
        super("Date from past " + time);
    }
}
