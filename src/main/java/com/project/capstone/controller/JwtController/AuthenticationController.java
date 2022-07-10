package com.project.capstone.controller.JwtController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.UserRequest;
import com.project.capstone.response.RegistrationRequest;
import com.project.capstone.response.TokenResponse;
import com.project.capstone.response.UsernamePassword;
import com.project.capstone.service.implementations.AuthService;
import com.project.capstone.util.ResponseUtil;

@Slf4j
@CrossOrigin(origins = "https://springboot-postgresql-capstone.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticateUser(@RequestBody UserRequest loginRequest) {
        log.info("Incoming password login request.");
        TokenResponse tokenResponse = authenticationService.generatedToken(loginRequest);
        log.info("Successfully authenticated.");
        return ResponseEntity.ok(tokenResponse);
    }

  
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) {
        try {
            User user = authenticationService.register(request);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);        
        }
    }
}
