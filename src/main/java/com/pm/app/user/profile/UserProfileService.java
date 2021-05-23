package com.pm.app.user.profile;

import com.pm.app.user.User;
import com.pm.app.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserProfileService {

    private static final String PROFILE_NOT_FOUND = "Profile with %s '%s' is not found";
    private final UserProfileRepository profileRepository;

    public UserProfile loadProfileByNickname(String s) throws IllegalStateException {
        return profileRepository.findProfileByNickname(s)
                .orElseThrow(()->new IllegalStateException(String.format(PROFILE_NOT_FOUND, "nickname", s)));
    }

    public UserProfile loadProfileByUser(User user) throws IllegalStateException{
        return profileRepository.findProfileByUser(user)
                .orElseThrow(()->new IllegalStateException(String.format(PROFILE_NOT_FOUND, "username", user.getUsername())));
    }

    public List<UserProfile> listAll() {
        return profileRepository.findAll();
    }

    public UserProfile get(Integer id) { return profileRepository.findById(id).get(); }

    public boolean isProfileExists(Integer id) {
        return profileRepository.findById(id).isPresent();
    }

    public boolean isNicknameExist(String nickname){
        return profileRepository.findProfileByNickname(nickname).isPresent();
    }

    public boolean isUserExist(User user){
        return profileRepository.findProfileByUser(user).isPresent();
    }

    public boolean save(UserProfile userProfile){
        UserProfile newUserProfile = profileRepository.save(userProfile);
        return isProfileExists(newUserProfile.getId());
    }
}
