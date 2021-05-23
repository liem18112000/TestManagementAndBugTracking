package com.pm.app.user.profile;

import com.pm.app.user.User;
import com.pm.app.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserProfileEndPoint {

    private static final String CREATE_PROFILE_SUCCESS = "Profile create successfully";
    private static final String CREATE_PROFILE_FAILED = "Profile create failed";

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/profile")
    public List<UserProfile> all() {
        return profileService.listAll();
    }

    @GetMapping("/api/profile/{id}")
    public ResponseEntity<UserProfile> show(@PathVariable(name="id") Integer id){
        return new ResponseEntity<UserProfile>(
                profileService.get(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/api/profile")
    public ResponseEntity<String> save(@RequestBody CreateProfileRequest request){
        User owner = userService.get(request.getUser_id());
        UserProfile newUserProfile = new UserProfile(
                owner,
                request.getNickname(),
                request.getGender(),
                request.getDescription(),
                request.getImage()
        );

        profileService.save(newUserProfile);

        if(!profileService.isProfileExists(newUserProfile.getId())){
            return new ResponseEntity<String>(
                    CREATE_PROFILE_FAILED,
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<String>(
                CREATE_PROFILE_SUCCESS,
                HttpStatus.OK
        );
    }

}
