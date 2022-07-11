package com.project.capstone.service;

import java.util.List;
import java.util.Optional;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.JadwalRequest;
import com.project.capstone.repository.DokterRepository;
import com.project.capstone.repository.JadwalRepository;
import com.project.capstone.repository.PasienRepository;
import com.project.capstone.repository.UserRepository;
import com.project.capstone.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JadwalService {
    @Autowired
    private JadwalRepository jadwalRepository;
    @Autowired
    private DokterRepository dokterRepository;
    @Autowired
    private PasienRepository pasienRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    public JadwalService(JadwalRepository jadwalRepository) {
        this.jadwalRepository = jadwalRepository;
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all jadwal");
        //return ResponseUtil.build("Success", jadwalRepository.findAll(), HttpStatus.OK);
        return ResponseEntity.ok().body(jadwalRepository.findAll());
    }

    public ResponseEntity<Object> save(JadwalRequest request){
        try{
        // log.info("Get Dokter: {}");
        Dokter dkt = dokterRepository.findOne(request.getDokterId())
            .orElseThrow(()-> new Exception("Dokter Id "+ request.getDokterId() + "Not Found"));

        // log.info("Get Pasien: {}");
        Pasien pasien = pasienRepository.findOne(request.getPasienId())
            .orElseThrow(()-> new Exception("Pasien Id "+ request.getDokterId() + "Not Found"));

        log.info("Save new jadwal: {}",request);
        
        Jadwal jadwal = Jadwal.builder()
            .dokter(dkt)
            .pasien(pasien)
            .nourut(request.getNourut())
            .jp(request.getJp())
            .tanggal(request.getTanggal())
            .controll(request.getControll())
            .catatan(request.getCatatan())
            .diagnosa(request.getDiagnosa())
            .build();
        
            jadwal = jadwalRepository.save(jadwal);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
     
        }

    }
    

    public ResponseEntity<Object> getJadwal(Long jadwalId) {
        return ResponseEntity.ok().body(jadwalRepository.findById(jadwalId));
    }

    public ResponseEntity<Object> deleteJadwal(Long jadwalId) {
        log.info("Find jadwal by jadwal id for delete: {}", jadwalId);
        try {
            jadwalRepository.delete(jadwalId);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }

    public ResponseEntity<Object> getJadwalById(Long id) {
        log.info("Find jadwal detail by jadwal id: {}", id);
        Optional<Jadwal> jadwal = jadwalRepository.findOne(id);
        if(jadwal.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateJadwal(JadwalRequest request, Long id) {
        try {

            
            log.info("Update jadwal: {}", request);
            Optional<Jadwal> jadwal = jadwalRepository.findOne(id);

            Dokter dkt = dokterRepository.findOne(request.getDokterId())
                .orElseThrow(()-> new Exception("Dokter Id "+ request.getDokterId() + "Not Found"));

  
            Pasien pasien = pasienRepository.findOne(request.getPasienId())
                .orElseThrow(()-> new Exception("Pasien Id "+ request.getDokterId() + "Not Found"));
            if (jadwal.isEmpty()) {
                log.info("Jadwal not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            jadwal.get().setDokter(dkt);
            jadwal.get().setPasien(pasien);
            jadwal.get().setNourut(request.getNourut());
            jadwal.get().setJp(request.getJp());
            jadwal.get().setTanggal(request.getTanggal());
            jadwalRepository.save(jadwal.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update jadwal, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateJadwalByDokter(JadwalRequest request, Long id) {
        try {

            log.info("Update jadwal: {}", request);
            Optional<Jadwal> jadwal = jadwalRepository.findOne(id);
            if (jadwal.isEmpty()) {
                log.info("Jadwal not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            
            jadwal.get().setControll(request.getControll());
            jadwal.get().setCatatan(request.getCatatan());
            jadwal.get().setDiagnosa(request.getDiagnosa());
            jadwalRepository.save(jadwal.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update jadwal, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Jadwal> getJadwalByUser(String userId) {
        try {
            Long id = Long.parseLong(userId);
            log.info("Get user {}", id);
            Optional<User> user = userRepository.findOne(id);
            if (user.isEmpty()) {
                log.info("user not found");
            }
            List<Jadwal> jadwal = jadwalRepository.findJadwalByUser(id);

            if(jadwal.isEmpty()) throw new Exception("JADWAL BY DOKTER IS EMPTY");
            return jadwal;
        } catch (Exception e) {
            log.error("Get course taken by user error");
            throw new RuntimeException(e.getMessage(), e);
        }
    }
   
}