// package com.project.demo.service;

// import org.springframework.security.core.userdetails.UserDetailsService;

// public interface UserService extends UserDetailsService{

// }

package com.project.capstone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.UserRequest;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> save(UserRequest request) {
        log.info("Save new user: {}", request);
        User user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .build();
        try {
            user = userRepository.save(user);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAllUser() {
        log.info("Get all users");
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getUser(Long userId) {
        return ResponseEntity.ok().body(userRepository.findById(userId));
    }

    public ResponseEntity<Object> getUserDetail(Long id) {
        log.info("Find user detail by user id: {}",id);
        Optional<User> user = userRepository.findOne(id);
        if (user.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, user.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteUser(Long Id) {
        log.info("Find user by user id for delete: {}", Id);
        try {
            userRepository.delete(Id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }
    
    public ResponseEntity<Object> updateUser(User request, Long id) {
        try {
            log.info("Update user: {}", request);
            Optional<User> user = userRepository.findOne(id);
            if (user.isEmpty()) {
                log.info("user not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }

            user.get().setUsername(request.getUsername());;
            user.get().setPassword(request.getPassword());
            // user.get().setRole(request.getRole());
            userRepository.save(user.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, user.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update category, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}