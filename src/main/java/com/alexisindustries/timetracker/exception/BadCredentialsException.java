package com.alexisindustries.timetracker.exception;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("Username or password is incorrect, please check your credentials");
    }
}
