package com.pm.app.user.auth;

import com.pm.app.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {
    @Autowired
    private final UserService userService;

    @PostMapping("/api/authenticate/basic/header")
    public ResponseEntity<String> basicAuthenticationWithHeader(
            @RequestHeader(name="username") String username,
            @RequestHeader(name="password") String password)
    {
        return login(username, password);
    }

    @PostMapping("/api/authenticate/basic/json")
    public ResponseEntity<String> basicAuthenticationWithJSON(@RequestBody AuthenticateRequest request){
        final String username = request.getUsername();
        final String password = request.getPassword();
        return login(username, password);
    }

    private ResponseEntity<String> login(String username, String password) {

        if(userService.login(username, password)){
            return new ResponseEntity<String>(
                    "Login successfully",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<String>(
                "Login failed",
                HttpStatus.NOT_FOUND
        );
    }
}
