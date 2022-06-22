package com.project.capstone.service;

import java.util.Optional;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dto.DokterRequest;
import com.project.capstone.repository.DokterRepository;
import com.project.capstone.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DokterService {
    private DokterRepository dokterRepository;

    @Autowired
    public DokterService(DokterRepository dokterRepository) {
        this.dokterRepository = dokterRepository;
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all dokter");
        //return ResponseUtil.build("Success", customerRepository.findAll(), HttpStatus.OK);
        return ResponseEntity.ok().body(dokterRepository.findAll());
    }

    public ResponseEntity<Object> save(DokterRequest request) {
        log.info("Save new Dokter: {}", request);
        Dokter dokter = Dokter.builder()
            .namadokter(request.getNamadokter())
            .spesialis(request.getSpesialis())
            .srp(request.getSrp())
            .jeniskelamin(request.getJeniskelamin())
            .telp(request.getTelp())
            .build();
        try {
            dokter = dokterRepository.save(dokter);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getDokter(Long dokterId) {
        return ResponseEntity.ok().body(dokterRepository.findById(dokterId));
    }

    public ResponseEntity<Object> deleteDokter(Long id) {
        log.info("Find dokter by dokter id for delete: {}", id);
        try {
            dokterRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }

    public ResponseEntity<Object> getDokterById(Long id) {
        log.info("Find Dokter detail by dokter id: {}",id);
        Optional<Dokter> dokter = dokterRepository.findOne(id);
        if (dokter.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDokter(Dokter request, Long id) {
        try {
            log.info("Update dokter: {}", request);
            Optional<Dokter> dokter = dokterRepository.findOne(id);
            if (dokter.isEmpty()) {
                log.info("dokter not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }

            dokter.get().setNamadokter(request.getNamadokter());
            dokter.get().setSpesialis(request.getSpesialis());
            dokter.get().setSrp(request.getSrp());
            dokter.get().setJeniskelamin(request.getJeniskelamin());
            dokter.get().setTelp(request.getTelp());
    
            // user.get().setRole(request.getRole());
            dokterRepository.save(dokter.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update dokter, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // public ResponseEntity<Object> updatedokter(DokterRequest request, Long dokterId) {
    //     log.info("Update nama: {}", request);
    //     Optional<Dokter> dokter = dokterRepository.findById(dokterId);
    //     if (dokter.isEmpty()) return ResponseEntity.badRequest().body(Map.ofEntries(Map.entry("message", "Data not found")));

    //     dokter.get().setNamadokter(request.getNamadokter());
    //     dokter.get().setSpesialis(request.getSpesialis());
    //     // dokter.get().setSrp(request.getSrp());
    //     // dokter.get().setJeniskelamin(request.getJeniskelamin());
    //     // dokter.get().setTelp(request.getTelp());
    //     // dokterRepository.save(dokter.get());
    //     return ResponseEntity.ok().body(dokter.get());
    // }
   
}