package com.project.capstone.service;

import java.util.Optional;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.domain.dto.PasienRequest;
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
public class PasienService {
    private PasienRepository pasienRepository;

    @Autowired
    public PasienService(PasienRepository pasienRepository) {
        this.pasienRepository = pasienRepository;
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all pasien");
        //return ResponseUtil.build("Success", pasienRepository.findAll(), HttpStatus.OK);
        return ResponseEntity.ok().body(pasienRepository.findAll());
    }

    public ResponseEntity<Object> save(PasienRequest request) {
        log.info("Save new pasien: {}", request);
        Pasien pasien = Pasien.builder()
            .namapasien(request.getNamapasien())
            .nik(request.getNik())
            .umur(request.getUmur())
            .jeniskelamin(request.getJeniskelamin())
            .telp(request.getTelp())
            .build();
        try {
            pasien = pasienRepository.save(pasien);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, pasien, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getPasien(Long pasienId) {
        return ResponseEntity.ok().body(pasienRepository.findById(pasienId));
    }

    public ResponseEntity<Object> getPasienById(Long id) {
        log.info("Find pasien detail by pasien id: {}",id);
        Optional<Pasien> pasien = pasienRepository.findOne(id);
        if (pasien.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, pasien.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deletePasien(Long id) {
        log.info("Find Pasien by Pasien id for delete: {}", id);
        try {
            pasienRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }
    // public ResponseEntity<Object> getPasienById(Long id) {
    //     log.info("Find user detail by user id: {}", id);
    //     Optional<Pasien> pasien = pasienRepository.findById(id);
    //     if(pasien.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

    //     return ResponseUtil
    //     .build(AppConstant.ResponseCode.SUCCESS, pasien.get(), HttpStatus.OK);
    // }

    public ResponseEntity<Object> updatePasien(Pasien request, Long id) {
        try {
            log.info("Update pasien: {}", request);
            Optional<Pasien> pasien = pasienRepository.findOne(id);
            if (pasien.isEmpty()) {
                log.info("pasien not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }

            pasien.get().setNamapasien(request.getNamapasien());
            pasien.get().setNik(request.getNik());
            pasien.get().setUmur(request.getUmur());
            pasien.get().setJeniskelamin(request.getJeniskelamin());
            pasien.get().setTelp(request.getTelp());
            // user.get().setRole(request.getRole());
            pasienRepository.save(pasien.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, pasien.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update pasien, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<Object> updateNamapasien(PasienRequest request, Long pasienId) {
    //     log.info("Update nama: {}", request);
    //     Optional<Pasien> pasien = pasienRepository.findById(pasienId);
    //     if (pasien.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

    //     pasien.get().setNamapasien(request.getNamapasien());
    //     pasienRepository.save(pasien.get());
    //     return ResponseEntity.ok().body(pasien.get());
    // }

    // public ResponseEntity<Object> addPasien(PasienRequest request) {
    //     log.info("Add pasien");
    //     Pasien pasien = Pasien.builder()
    //         .nama(request.getNama())
    //         .jeniskelamin(request.getJeniskelamin())
    //         .telp(request.getTelp())
    //         .build();
    //     pasien = pasienRepository.save(pasien);
    //     return ResponseUtil.build("Success", pasien, HttpStatus.OK);
    // }
   
}