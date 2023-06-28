package com.example.aiprojekt.Exception;

import java.util.Date;

public class DateFromPastException extends RuntimeException {
    public DateFromPastException(Date time) {
        super("Date from past" + time);
    }
}
