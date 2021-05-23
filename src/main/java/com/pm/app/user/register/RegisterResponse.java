package com.pm.app.user.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class RegisterResponse {
    private final boolean isSuccess;
    private final String message;
}
