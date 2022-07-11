package com.project.capstone.controller;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dto.DokterRequest;
import com.project.capstone.security.jwt.JwtProvider;
import com.project.capstone.service.DokterService;
import com.project.capstone.util.ResponseUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/dokter")
public class DokterController {

    @Autowired
    private DokterService dokterService;
    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllDokter() {
        return dokterService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getDokterById(@PathVariable(value = "id") Long dokterId) {
        return dokterService.getDokterById(dokterId);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createDokter(@RequestBody DokterRequest request) {
        return dokterService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteDokter(@PathVariable(value = "id") Long dokterId) {
        return dokterService.deleteDokter(dokterId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updatedokter(@PathVariable(value = "id") Long dokterId,
                                              @RequestBody DokterRequest request) {
        return dokterService.updateDokter(request, dokterId);
    }
    @GetMapping(value = "/getbydokter")
    @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<?> getJadwalByUser(HttpServletRequest request) {
        try {
            List<Dokter> dokter = dokterService.getDokterByUser(getId(request));
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, dokter, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.OK);
        }
    }

    private String getId(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token = bearerToken.substring(7);
        return jwtProvider.getId(token);
    }
    
}
