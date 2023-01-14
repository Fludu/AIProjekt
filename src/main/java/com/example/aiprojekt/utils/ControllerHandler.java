package com.example.aiprojekt.utils;

import com.example.aiprojekt.Exception.CompanyNotFoundException;
import com.example.aiprojekt.Exception.EmailBusyException;
import com.example.aiprojekt.Exception.EmployeeNotFoundException;
import com.example.aiprojekt.Exception.RoleNotFoundException;
import com.example.aiprojekt.Exception.SalaryMustBePositive;
import com.example.aiprojekt.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleCompanyNotFoundException(CompanyNotFoundException exception) {
        return new ResponseEntity<>(new ErrorMessageDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return new ResponseEntity<>(new ErrorMessageDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleRoleNotFoundException(RoleNotFoundException exception) {
        return new ResponseEntity<>(new ErrorMessageDto(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SalaryMustBePositive.class)
    public ResponseEntity<ErrorMessageDto> handleSalaryMustBePositiveException(SalaryMustBePositive exception) {
        return new ResponseEntity<>(new ErrorMessageDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailBusyException.class)
    public ResponseEntity<ErrorMessageDto> handleEmailBusyException(EmailBusyException exception) {
        return new ResponseEntity<>(new ErrorMessageDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
