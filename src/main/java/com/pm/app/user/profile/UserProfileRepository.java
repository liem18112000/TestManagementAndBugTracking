package com.pm.app.user.profile;

import com.pm.app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findProfileByNickname(String nickname);
    Optional<UserProfile> findProfileByUser(User user);
}
