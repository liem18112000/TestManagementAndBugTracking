package com.pm.app.user.register;


import com.pm.app.user.User;
import com.pm.app.user.UserService;
import com.pm.app.user.register.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    private final EmailValidator emailValidator;
    private final UserService service;

    public boolean validateEmail(String email){
        return emailValidator.test(email);
    }

    public boolean validatePasswordConfirmation(String password, String confirmation){
        return password.equals(confirmation);
    }

    public void totalValidation(RegisterRequest request){
        if(!validateEmail(request.getEmail())){
            throw new IllegalStateException("Email is not valid");
        }

        if(!validatePasswordConfirmation(request.getPassword(), request.getConfirmPassword())){
            throw new IllegalStateException("Password confirmation does not match password");
        }
    }

    public boolean register(RegisterRequest request) {
        totalValidation(request);
        return service.register(
                new User(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }
}
