package com.project.capstone.service;

import java.util.List;
import java.util.Optional;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dao.User;
import com.project.capstone.domain.dto.DokterRequest;
import com.project.capstone.repository.DokterRepository;
import com.project.capstone.repository.UserRepository;
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
    @Autowired
    private DokterRepository dokterRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DokterService(DokterRepository dokterRepository) {
        this.dokterRepository = dokterRepository;
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all dokter");
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokterRepository.findAll(), HttpStatus.OK);
        // return ResponseEntity.ok().body(dokterRepository.findAll());
    }

    public ResponseEntity<Object> save(DokterRequest request) {
        try {
            log.info("Search srp in database");
            if (dokterRepository.findDokterBySrp(request.getSrp()) != null) {
                throw new Exception("Dokter IS ALREADY EXIST");
            }

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(()-> new Exception("Dokter Id "+ request.getUserId() + "Not Found"));

        log.info("Save new Dokter: {}", request);
        Dokter dokter = Dokter.builder()
            .user(user)
            .namadokter(request.getNamadokter())
            .spesialis(request.getSpesialis())
            .srp(request.getSrp())
            .build();
        
            dokter = dokterRepository.save(dokter);

            user.setDokter(dokter);
            userRepository.save(user);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getDokter(Long dokterId) {
        return ResponseEntity.ok().body(dokterRepository.findById(dokterId));
    }

    public ResponseEntity<Object> deleteDokter(Long Id) {
        log.info("Find dokter by dokter id for delete: {}", Id);
        try {
            dokterRepository.deleteById(Id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }

    public ResponseEntity<Object> getDokterById(Long id) {
        log.info("Find Dokter detail by dokter id: {}",id);
        Optional<Dokter> dokter = dokterRepository.findById(id);
        if (dokter.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> updateDokter(DokterRequest request, Long id) {
        try {
            log.info("Update dokter: {}", request);

            User user = userRepository.findById(request.getUserId())
            .orElseThrow(()-> new Exception("Dokter Id "+ request.getUserId() + "Not Found"));

            Optional<Dokter> dokter = dokterRepository.findById(id);
            if (dokter.isEmpty()) {
                log.info("dokter not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }

            dokter.get().setUser(user);
            dokter.get().setNamadokter(request.getNamadokter());
            dokter.get().setSpesialis(request.getSpesialis());
            dokter.get().setSrp(request.getSrp());
    
            // user.get().setRole(request.getRole());
            dokterRepository.save(dokter.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update dokter, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Dokter> getDokterByUser(String userId) {
        try {
            Long id = Long.parseLong(userId);
            log.info("Get user {}", id);
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                log.info("user not found");
            }
            List<Dokter> dokter = dokterRepository.findDokterByUser(id);

            if(dokter.isEmpty()) throw new Exception("DOKTER NOT FOUND");
            return dokter;
        } catch (Exception e) {
            log.error("Get course taken by user error");
            throw new RuntimeException(e.getMessage(), e);
        }
    }
   
}