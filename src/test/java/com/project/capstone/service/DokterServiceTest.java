package com.project.capstone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

import com.project.capstone.domain.common.ApiResponse;
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.DokterRequest;
import com.project.capstone.repository.DokterRepository;
import com.project.capstone.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DokterService.class)
public class DokterServiceTest {

    @MockBean
    private DokterRepository dokterRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private DokterService dokterService;

    @Test
    void getAllSuccess_Test() {
        when(dokterRepository.findAll())
            .thenReturn(List.of(Dokter.builder()
                .id(1L)
                .namadokter("lala")
                .spesialis("bedah jantung")
                .srp((long) 435827)
                .build()));
        
        ResponseEntity<Object> responseEntity = dokterService.getAll();
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        List<Dokter> dokter = (List<Dokter>) Objects.requireNonNull(response).getData();
        
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1, dokter.size());
    }

    @Test
    void testAddDokter() {
        
        User user = User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        when(dokterRepository.save(any()))
            .thenReturn(Dokter.builder()
                .id(1L)
                .user(user)
                .namadokter("lulu")
                .spesialis("bedah jantung")
                .srp((long) 435827)
                .build());

        ResponseEntity<Object> responseEntity = dokterService.save(
                                        DokterRequest.builder()
                                        .userId(user.getId())
                                        .namadokter("lulu")
                                        .spesialis("bedah jantung")
                                        .srp((long) 435827)
                                        .build());
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Dokter dokter = (Dokter) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, dokter.getId());
        assertEquals("lulu", dokter.getNamadokter());
        assertEquals("bedah jantung", dokter.getSpesialis());
        assertEquals(435827, dokter.getSrp());

       }

       @Test
    void testGetDokterById() {
        User user = User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        when(dokterRepository.findById(anyLong()))
            .thenReturn(Optional.of(Dokter.builder()
                .id(1L)
                .user(user)
                .namadokter("lulu")
                .spesialis("bedah jantung")
                .srp((long) 435827)
                .build()));

        ResponseEntity<Object> responseEntity = dokterService.getDokterById(1L);
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Dokter dokter = (Dokter) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, dokter.getId());
        assertEquals("lulu",dokter.getNamadokter());
        assertEquals("bedah jantung",dokter.getSpesialis());
        assertEquals((long) 435827,dokter.getSrp());

    }

       @Test
       void testDeleteDokter() {
            User user = User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build();

            when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

           when(dokterRepository.findById(anyLong()))
               .thenReturn(Optional.of(Dokter.builder()
                   .id(1L)
                   .user(user)
                   .namadokter("lala")
                   .spesialis("bedah jantung")
                   .srp((long) 435827)
                   .build()));
           doNothing().when(dokterRepository).deleteById(anyLong());
   
           ResponseEntity<Object> responseEntity = dokterService.deleteDokter(1L);
           ApiResponse response = (ApiResponse) responseEntity.getBody();
           assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
           assertEquals("Success!", response.getMessage());
           verify(dokterRepository, times(1)).deleteById(anyLong());
       }
   
    @Test
        void testUpdateDokter() {
            User user = User.builder()
                .id(1L)
                .username("lala")
                .password("1235435")
                .build();

            when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

            Dokter dokter = Dokter.builder()
                .id(1L)
                .user(user)
                .namadokter("lala")
                .spesialis("bedah jantung")
                .srp((long) 435827)
                .build();

            when(dokterRepository.findById(anyLong())).thenReturn(Optional.of(dokter));
            when(dokterRepository.save(any())).thenReturn(dokter);
            ResponseEntity<Object> responseEntity = dokterService.updateDokter(
                                                DokterRequest.builder()
                                                .userId(user.getId())
                                                .namadokter("lala")
                                                .spesialis("bedah jantung")
                                                .srp((long) 435827)
                                                .build(), 1L);
            ApiResponse response = (ApiResponse) responseEntity.getBody();
            Dokter data = (Dokter) Objects.requireNonNull(response).getData();
            assertEquals("Success!", response.getMessage());
            assertEquals(1L, data.getId());
            assertEquals("lala", data.getNamadokter());
            assertEquals("bedah jantung", data.getSpesialis());
            assertEquals(435827, data.getSrp());

        }

            
}
