package com.project.capstone.controller;

import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.UserRequest;
import com.project.capstone.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
//@RequestMapping("/v1/user")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long userId) {
        return userService.deleteUser(userId);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUserDetail(@PathVariable(value = "id") Long userId) {
        return userService.getUserDetail(userId);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long userId,
                                              @RequestBody User request) {
        return userService.updateUser(request, userId);
    }
    

    // @Autowired
    // private UserServiceImpl userServiceImpl;
    

    // @GetMapping(value = "")
    // public ResponseEntity<Object> getAllUser() {
    //     return userServiceImpl.getAll();
    // }

    // @PostMapping(value = "")
    // public ResponseEntity<Object> createUser(@RequestBody UserRequest request) {
    //     return userServiceImpl.save(request);
    // }

    // @DeleteMapping(value = "/{id}")
    // public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long userId) {
    //     return userServiceImpl.deleteUser(userId);
    // }

    // @PostMapping(value = "/{id}")
    // public ResponseEntity<Object> updateUsername(@PathVariable(value = "id") Long userId,
    //                                           @RequestBody UserRequest request) {
    //     return userServiceImpl.updateUsername(request, userId);
    // }    
}
