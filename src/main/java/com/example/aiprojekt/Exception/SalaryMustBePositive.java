package com.example.aiprojekt.Exception;

public class SalaryMustBePositive extends RuntimeException {
    public SalaryMustBePositive() {
        super("Salary must be positive");
    }

}