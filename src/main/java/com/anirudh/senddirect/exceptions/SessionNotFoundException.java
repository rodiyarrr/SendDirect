package com.anirudh.senddirect.exceptions;

public class SessionNotFoundException extends RuntimeException{

    public SessionNotFoundException(String message) {
        super(message);
    }
}
