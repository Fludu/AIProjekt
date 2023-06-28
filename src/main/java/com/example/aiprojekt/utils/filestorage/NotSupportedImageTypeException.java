package com.example.aiprojekt.utils.filestorage;

public class NotSupportedImageTypeException extends RuntimeException {

    NotSupportedImageTypeException(String contentType) {
        super("Not supported image type " + contentType);
    }
}