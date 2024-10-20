package com.alexisindustries.timetracker.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super(String.format("User with username = %s not found", username));
    }
}
