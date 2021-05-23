package com.pm.app.user.profile;

import com.pm.app.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProfileRequest {
    private final String nickname;
    private final Gender gender;
    private final String image;
    private final Integer user_id;
    private final String description;
}
