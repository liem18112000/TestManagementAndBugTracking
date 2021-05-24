package com.pm.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserEndPoint {

    @Autowired
    private UserService service;

    @GetMapping("/api/user")
    public List<User> read() {
        return service.listAll();
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User target = service.get(id);
            return new ResponseEntity<User>(target, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/user/{username}")
    public ResponseEntity<User> get(@PathVariable String username) {
        try {
            return new ResponseEntity<User>((User) service.loadUserByUsername(username), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
}
