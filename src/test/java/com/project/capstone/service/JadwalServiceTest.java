package com.project.capstone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.JadwalRequest;
import com.project.capstone.repository.DokterRepository;
import com.project.capstone.repository.JadwalRepository;
import com.project.capstone.repository.PasienRepository;
import com.project.capstone.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JadwalService.class)
public class JadwalServiceTest {
    @MockBean
    private JadwalRepository jadwalRepository;

    @MockBean
    private DokterRepository dokterRepository;

    @MockBean
    private PasienRepository pasienRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private JadwalService jadwalService;

    @Test
    void getAllSuccess_Test() {

        when(jadwalRepository.findAll())
            .thenReturn(List.of(Jadwal.builder()
                .id(1L)
                .nourut(321)
                .jp("Rawat Jalan")
                .tanggal(LocalDate.now())
                .controll(LocalDate.now())
                .catatan("Minum obat rutin")
                .diagnosa("Hipertensi")
                .build()));
        
        ResponseEntity<Object> responseEntity = jadwalService.getAll();
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        List<Jadwal> jadwal = (List<Jadwal>) Objects.requireNonNull(response).getData();
        
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1, jadwal.size());
    }
    @Test
    void testAddJadwal() {
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

        when(jadwalRepository.save(any()))
            .thenReturn(Jadwal.builder()
                .id(1L)
                .dokter(dokter)
                .pasien(pasien)
                .nourut(321)
                .jp("Rawat Jalan")
                .tanggal(LocalDate.now())
                .controll(LocalDate.now())
                .catatan("Minum obat rutin")
                .diagnosa("Hipertensi")
                .build());

        ResponseEntity<Object> responseEntity = jadwalService.save(
                                        JadwalRequest.builder()
                                        .dokterId(dokter.getId())
                                        .pasienId(pasien.getId())
                                        .nourut(321)
                                        .jp("Rawat Jalan")
                                        .tanggal(LocalDate.now())
                                        .controll(LocalDate.now())
                                        .catatan("Minum obat rutin")
                                        .diagnosa("Hipertensi")
                                        .build());
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Jadwal jadwal = (Jadwal) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, jadwal.getId());
        assertEquals(321, jadwal.getNourut());
        assertEquals("Rawat Jalan", jadwal.getJp());
        assertEquals(LocalDate.now(), jadwal.getTanggal());
        assertEquals(LocalDate.now(), jadwal.getControll());
        assertEquals("Minum obat rutin", jadwal.getCatatan());
        assertEquals("Hipertensi", jadwal.getDiagnosa());

       }

       @Test
    void testGetJadwalById() {
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

        when(jadwalRepository.findById(anyLong()))
            .thenReturn(Optional.of(Jadwal.builder()
                .id(1L)
                .dokter(dokter)
                .pasien(pasien)
                .nourut(321)
                .jp("Rawat Jalan")
                .tanggal(LocalDate.now())
                .controll(LocalDate.now())
                .catatan("Minum obat rutin")
                .diagnosa("Hipertensi")
                .build()));

        ResponseEntity<Object> responseEntity = jadwalService.getJadwalById(1L);
        ApiResponse response = (ApiResponse) responseEntity.getBody();
        Jadwal jadwal = (Jadwal) Objects.requireNonNull(response).getData();

        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("Success!", response.getMessage());
        assertEquals(1L, jadwal.getId());
        assertEquals(321, jadwal.getNourut());
        assertEquals("Rawat Jalan", jadwal.getJp());
        assertEquals(LocalDate.now(), jadwal.getTanggal());
        assertEquals(LocalDate.now(), jadwal.getControll());
        assertEquals("Minum obat rutin", jadwal.getCatatan());
        assertEquals("Hipertensi", jadwal.getDiagnosa());

    }

       @Test
       void testDeleteJadwal() {
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

           when(jadwalRepository.findById(anyLong()))
               .thenReturn(Optional.of(Jadwal.builder()
                    .id(1L)
                    .dokter(dokter)
                    .pasien(pasien)
                    .nourut(321)
                    .jp("Rawat Jalan")
                    .tanggal(LocalDate.now())
                    .controll(LocalDate.now())
                    .catatan("Minum obat rutin")
                    .diagnosa("Hipertensi")
                   .build()));
           doNothing().when(jadwalRepository).deleteById(anyLong());
   
           ResponseEntity<Object> responseEntity = jadwalService.deleteJadwal(1L);
           ApiResponse response = (ApiResponse) responseEntity.getBody();
           assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
           assertEquals("Success!", response.getMessage());
           verify(jadwalRepository, times(1)).deleteById(anyLong());
       }
   
    @Test
        void testUpdateCategory() {
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

            Jadwal jadwal = Jadwal.builder()
                    .id(1L)
                    .dokter(dokter)
                    .pasien(pasien)
                    .nourut(321)
                    .jp("Rawat Jalan")
                    .tanggal(LocalDate.now())
                    .controll(LocalDate.now())
                    .catatan("Minum obat rutin")
                    .diagnosa("Hipertensi")
                    .build();

            when(jadwalRepository.findById(anyLong())).thenReturn(Optional.of(jadwal));
            when(jadwalRepository.save(any())).thenReturn(jadwal);
            ResponseEntity<Object> responseEntity = jadwalService.updateJadwal(
                                                JadwalRequest.builder()
                                                .dokterId(dokter.getId())
                                                .pasienId(pasien.getId())
                                                .nourut(321)
                                                .jp("Rawat Jalan")
                                                .tanggal(LocalDate.now())
                                                .controll(LocalDate.now())
                                                .catatan("Minum obat rutin")
                                                .diagnosa("Hipertensi")
                                                .build(), 1L);
            ApiResponse response = (ApiResponse) responseEntity.getBody();
            Jadwal data = (Jadwal) Objects.requireNonNull(response).getData();
            assertEquals("Success!", response.getMessage());
            assertEquals(1L, data.getId());
            assertEquals(321, data.getNourut());
            assertEquals("Rawat Jalan", data.getJp());
            assertEquals(LocalDate.now(), data.getTanggal());
            assertEquals(LocalDate.now(), data.getControll());
            assertEquals("Minum obat rutin", data.getCatatan());
            assertEquals("Hipertensi", data.getDiagnosa());

        }

}
