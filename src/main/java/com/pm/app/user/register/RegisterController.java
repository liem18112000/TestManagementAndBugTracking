package com.pm.app.user.register;

import com.pm.app.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegisterController {

    private static final String CREDENTIAL_EXIST = "%s '%s' has been taken";
    private static final String REGISTER_FAILED = "Register failed";
    private static final String REGISTER_SUCCESS = "Register successfully";

    @Autowired
    private final RegisterService registerService;

    @Autowired
    private final UserService userService;

    @PostMapping(path="/user/register/basic", name="basic_user_register")
    public ResponseEntity<RegisterResponse> basicRegister(@RequestBody RegisterRequest request){

        boolean flag = true;
        String message = REGISTER_SUCCESS;
        HttpStatus status = HttpStatus.OK;

        if(userService.isUsernameExist(request.getUsername())){
            message = String.format(CREDENTIAL_EXIST, "Username", request.getUsername());
            flag = false;
            status = HttpStatus.BAD_REQUEST;
        }else if(userService.isEmailExist(request.getEmail())){
            message = String.format(CREDENTIAL_EXIST, "Email", request.getUsername());
            flag = false;
            status = HttpStatus.BAD_REQUEST;
        }

        if(!registerService.register(request)){
            message = REGISTER_FAILED;
            flag = false;
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<RegisterResponse>(
                new RegisterResponse(
                        flag,
                        message
                        ),
                status
        );
    }

}
