package com.sysaid.assignment.controller;

import com.sysaid.assignment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/add-user/{user}")
    public ResponseEntity<?> addUser(@PathVariable ("user") String user){
        return userService.addUser(user);
    }
}
