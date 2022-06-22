package com.project.capstone.service;

import java.util.Optional;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.domain.dto.JadwalRequest;
import com.project.capstone.repository.DokterRepository;
import com.project.capstone.repository.JadwalRepository;
import com.project.capstone.repository.PasienRepository;
import com.project.capstone.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JadwalService {
    private JadwalRepository jadwalRepository;
    private DokterRepository dokterRepository;
    private PasienRepository pasienRepository;

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
        Dokter dkt = dokterRepository.findById(request.getDokterId())
            .orElseThrow(()-> new Exception("Dokter Id "+ request.getDokterId() + "Not Found"));

        // log.info("Get Pasien: {}");
        Pasien pasien = pasienRepository.findById(request.getPasienId())
            .orElseThrow(()-> new Exception("Pasien Id "+ request.getDokterId() + "Not Found"));

        log.info("Save new jadwal: {}",request);
        // Jadwal jadwal = new Jadwal();

        // jadwal.setDokter(dkt);
        // jadwal.setPasien(pasien);
        // jadwal.setNourut(request.getNourut());
        // jadwal.setJp(request.getJp());
        // jadwal.setTanggal(request.getTanggal());
        // jadwalRepository.save(jadwal);
        // return jadwal;
        // // return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal, HttpStatus.OK);
        // } catch (Exception e){
        //     log.error("Post jadwal error");
        //     throw new RuntimeException(e.getMessage(),e);
        //     // return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        // }
        Jadwal jadwal = Jadwal.builder()
            .dokter(dkt)
            .pasien(pasien)
            .nourut(request.getNourut())
            .jp(request.getJp())
            .tanggal(request.getTanggal())
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

    public ResponseEntity<Object> updateJadwal(Jadwal request, Long id) {
        try {
            log.info("Update jadwal: {}", request);
            Optional<Jadwal> jadwal = jadwalRepository.findById(id);
            if (jadwal.isEmpty()) {
                log.info("Jadwal not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            jadwal.get().setDokter(request.getDokter());
            jadwal.get().setPasien(request.getPasien());
            jadwal.get().setNourut(request.getNourut());
            jadwal.get().setJp(request.getJp());
            jadwal.get().setTanggal(request.getTanggal());
            // jadwal.get().setStatus(request.getStatus());
            // user.get().setRole(request.getRole());
            jadwalRepository.save(jadwal.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update jadwal, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<Object> updateJp(JadwalRequest request, Long jadwalId) {
    //     log.info("Update jenis perawatan: {}", request);
    //     Optional<Jadwal> jadwal = jadwalRepository.findById(jadwalId);
    //     if (jadwal.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

    //     jadwal.get().setJp(request.getJp());
    //     jadwalRepository.save(jadwal.get());
    //     return ResponseEntity.ok().body(jadwal.get());
    // }
   
}