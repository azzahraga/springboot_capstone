package com.project.capstone.service.implementations;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.capstone.domain.dao.Role;
import com.project.capstone.domain.dao.RoleEnum;
import com.project.capstone.domain.dao.User;
import com.project.capstone.repository.RoleRepository;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.response.RegistrationRequest;
import com.project.capstone.response.TokenResponse;
import com.project.capstone.response.UsernamePassword;
import com.project.capstone.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager; 
    private final JwtProvider jwtProvider; 
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    
    public User register(RegistrationRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        Set<Role> roles = new HashSet<>();
            if(req.getRoles() == null) {
                Role role = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));

                roles.add(role);
            }else {
                req.getRoles().forEach(inputRole -> {
                    Role role = roleRepository.findByName(inputRole)
                        .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
                    roles.add(role);
                });
            }
            user.setRoles(roles);
        return userRepository.save(user);
    }

    public TokenResponse generatedToken(UsernamePassword req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    req.getUsername(),
                    req.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generatedToken(authentication);
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwt);
            return tokenResponse;
        } catch(BadCredentialsException e) {
            log.error("Bad Credential", e);
            throw new RuntimeException(e.getMessage(), e);
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
