package com.pm.app.user.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserProfileController {
    @GetMapping("/user/profile")
    public String show() {
        return "profile/show";
    }
}
