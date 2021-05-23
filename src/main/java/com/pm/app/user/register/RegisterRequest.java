package com.pm.app.user.register;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegisterRequest {
    private final String username;
    private final String password;
    private final String email;
    private final String confirmPassword;
}
