package com.pm.app.user.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequest {
    private String username;
    private String password;
}
