package com.project.capstone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.capstone.domain.common.ApiResponse;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.UserRequest;
import com.project.capstone.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void getAllSuccess_Test() {
        when(userRepository.findAll())
            .thenReturn(List.of(User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build()));
        
        ResponseEntity<Object> responseEntity = userService.getAllUser();
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        List<User> user = (List<User>) Objects.requireNonNull(response).getData();
        
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1, user.size());
    }

    @Test
    void testAddUser() {
        
        when(userRepository.save(any()))
            .thenReturn(User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build());

        ResponseEntity<Object> responseEntity = userService.save(
                                        UserRequest.builder()
                                        .username("lala")
                                        .password("1235435")
                                        .build());
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        User user = (User) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, user.getId());
        assertEquals("lala", user.getUsername());
        assertEquals("1235435", user.getPassword());

       }

       @Test
    void testGetUserById() {
        when(userRepository.findById(anyLong()))
            .thenReturn(Optional.of(User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build()));

        ResponseEntity<Object> responseEntity = userService.getUserDetail(1L);
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        User user = (User) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, user.getId());
        assertEquals("lala", user.getUsername());
        assertEquals("1235435", user.getPassword());

    }

    @Test
       void testDeleteUser() {
           when(userRepository.findById(anyLong()))
               .thenReturn(Optional.of(User.builder()
                    .id(1L)
                    .username("lala")
                    .password("1235435")
                    .build()));
           doNothing().when(userRepository).deleteById(anyLong());
   
           ApiResponse response = (ApiResponse) userService.deleteUser(1L).getBody();
           assertEquals("Success!", response.getMessage());
           verify(userRepository, times(1)).deleteById(anyLong());
       }

       @Test
        void testUpdatePasien() {
            User user = User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build();

            when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
            when(userRepository.save(any())).thenReturn(user);
            ResponseEntity<Object> responseEntity = userService.updateUser(
                                                    UserRequest.builder()
                                                    .username("lala")
                                                    .password("1235435")
                                                    .build(), 1L);
            ApiResponse response = (ApiResponse) responseEntity.getBody();
            User data = (User) Objects.requireNonNull(response).getData();

            assertEquals(1L, data.getId());
            assertEquals("lala", data.getUsername());
            assertEquals("1235435", data.getPassword());
        }

}
