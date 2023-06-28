package com.example.aiprojekt.Exception;

public class CouldNotSaveFileException extends RuntimeException {

    public CouldNotSaveFileException(String filename) {
        super("Could not save file " + filename);
    }
}