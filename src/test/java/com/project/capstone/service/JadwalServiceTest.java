// package com.project.capstone.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.when;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Objects;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.project.capstone.domain.common.ApiResponse;
// import com.project.capstone.domain.dao.Jadwal;
// import com.project.capstone.repository.DokterRepository;
// import com.project.capstone.repository.JadwalRepository;
// import com.project.capstone.repository.PasienRepository;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = JadwalService.class)
// public class JadwalServiceTest {
//     @MockBean
//     private JadwalRepository jadwalRepository;

//     @MockBean
//     private DokterRepository dokterRepository;

//     @MockBean
//     private PasienRepository pasienRepository;

//     @Autowired
//     private JadwalService jadwalService;

//     @Test
//     void getAllSuccess_Test() {
//         // when(dokterRepository.findById(anyLong())
//         //     .then)

//         when(jadwalRepository.findAll())
//             .thenReturn(List.of(Jadwal.builder()
//                 .id(1L)
//                 .nourut(321)
//                 .jp("Rawat Jalan")
//                 .tanggal(LocalDate.now())
//                 .controll(LocalDate.of(2022, 4, 2))
//                 .catatan("Minum obat rutin")
//                 .diagnosa("Hipertensi")
//                 .build()));
        
//         ResponseEntity<Object> responseEntity = jadwalService.getAll();
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         List<Jadwal> jadwal = (List<Jadwal>) Objects.requireNonNull(response).getData();
        
//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals("Success!", response.getMessage());
//         assertEquals(1, jadwal.size());
//     }
// }
