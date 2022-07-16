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
import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.domain.dto.PasienRequest;
import com.project.capstone.repository.PasienRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PasienService.class)
public class PasienServiceTest {
    @MockBean
    private PasienRepository pasienRepository;

    @Autowired
    private PasienService pasienService;

    @Test
    void getAllSuccess_Test() {
        when(pasienRepository.findAll())
            .thenReturn(List.of(Pasien.builder()
                .id(1L)
                .namapasien("lala")
                .nik((long) 1235435)
                .umur(20)
                .telp((long) 876543453)
                .jeniskelamin("Perempuan")
                .alamat("Jl. sadewa 3")
                .build()));
        
        ResponseEntity<Object> responseEntity = pasienService.getAll();
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        List<Pasien> pasien = (List<Pasien>) Objects.requireNonNull(response).getData();
        
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1, pasien.size());
    }

    @Test
    void testAddPasien() {
        
        when(pasienRepository.save(any()))
            .thenReturn(Pasien.builder()
                .id(1L)
                .namapasien("lala")
                .nik((long) 1235435)
                .umur(20)
                .telp((long) 876543453)
                .jeniskelamin("Perempuan")
                .alamat("Jl. sadewa 3")
                .build());

        ResponseEntity<Object> responseEntity = pasienService.save(
                                        PasienRequest.builder()
                                        .namapasien("lala")
                                        .nik((long) 1235435)
                                        .umur(20)
                                        .telp((long) 876543453)
                                        .jeniskelamin("Perempuan")
                                        .alamat("Jl. sadewa 3")
                                        .build());
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Pasien pasien = (Pasien) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, pasien.getId());
        assertEquals("lala", pasien.getNamapasien());
        assertEquals(1235435, pasien.getNik());
        assertEquals(20, pasien.getUmur());
        assertEquals(876543453, pasien.getTelp());
        assertEquals("Perempuan", pasien.getJeniskelamin());
        assertEquals("Jl. sadewa 3", pasien.getAlamat());

       }

       @Test
    void testGetPasienById() {
        when(pasienRepository.findById(anyLong()))
            .thenReturn(Optional.of(Pasien.builder()
                .id(1L)
                .namapasien("lala")
                .nik((long) 1235435)
                .umur(20)
                .telp((long) 876543453)
                .jeniskelamin("Perempuan")
                .alamat("Jl. sadewa 3")
                .build()));

        ResponseEntity<Object> responseEntity = pasienService.getPasienById(1L);
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Pasien pasien = (Pasien) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, pasien.getId());
        assertEquals("lala", pasien.getNamapasien());
        assertEquals(1235435, pasien.getNik());
        assertEquals(20, pasien.getUmur());
        assertEquals(876543453, pasien.getTelp());
        assertEquals("Perempuan", pasien.getJeniskelamin());
        assertEquals("Jl. sadewa 3", pasien.getAlamat());

    }

    @Test
       void testDeletePasien() {
           when(pasienRepository.findById(anyLong()))
               .thenReturn(Optional.of(Pasien.builder()
                    .id(1L)
                    .namapasien("lala")
                    .nik((long) 1235435)
                    .umur(20)
                    .telp((long) 876543453)
                    .jeniskelamin("Perempuan")
                    .alamat("Jl. sadewa 3")
                    .build()));
           doNothing().when(pasienRepository).deleteById(anyLong());
   
           ApiResponse response = (ApiResponse) pasienService.deletePasien(1L).getBody();
           assertEquals("Success!", response.getMessage());
           verify(pasienRepository, times(1)).deleteById(anyLong());
       }
   
    @Test
        void testUpdatePasien() {
            Pasien pasien = Pasien.builder()
                .id(1L)
                .namapasien("lala")
                .nik((long) 1235435)
                .umur(20)
                .telp((long) 876543453)
                .jeniskelamin("Perempuan")
                .alamat("Jl. sadewa 3")
                .build();

            when(pasienRepository.findById(anyLong())).thenReturn(Optional.of(pasien));
            when(pasienRepository.save(any())).thenReturn(pasien);
            ResponseEntity<Object> responseEntity = pasienService.updatePasien(
                                                    PasienRequest.builder()
                                                    .namapasien("lala")
                                                    .nik((long) 1235435)
                                                    .umur(20)
                                                    .telp((long) 876543453)
                                                    .jeniskelamin("Perempuan")
                                                    .alamat("Jl. sadewa 3")
                                                    .build(), 1L);
            ApiResponse response = (ApiResponse) responseEntity.getBody();
            Pasien data = (Pasien) Objects.requireNonNull(response).getData();

            assertEquals(1L, data.getId());
            assertEquals("lala", data.getNamapasien());
            assertEquals(1235435, data.getNik());
            assertEquals(20, data.getUmur());
            assertEquals(876543453, data.getTelp());
            assertEquals("Perempuan", data.getJeniskelamin());
            assertEquals("Jl. sadewa 3", data.getAlamat());
        }


}
