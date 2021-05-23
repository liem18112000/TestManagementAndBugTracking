package com.pm.app.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repo;
    private final static String USERNAME_NOT_FOUND  = "User with username %s is not found";
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repo.findUserByUsername(s)
                .orElseThrow(()->new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND, s)));
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public User get(Integer id) { return repo.findById(id).get(); }

    public boolean isUserIDExists(Integer id) {
        return repo.findById(id).isPresent();
    }

    public boolean isEmailExist(String email){
        return repo.findUserByEmail(email).isPresent();
    }

    public boolean isUsernameExist(String username){
        return repo.findUserByUsername(username).isPresent();
    }

    public boolean register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = repo.save(user);
        return isUserIDExists(newUser.getId());
    }

    public boolean login(String username, String rawPassword){
        if(repo.findUserByUsername(username).isEmpty()){
            return false;
        }

        User target = (User) this.loadUserByUsername(username);
        return passwordEncoder.matches(rawPassword, target.getPassword()) ;
    }


}
