package com.project.capstone.controller;

import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.domain.dto.JadwalRequest;
import com.project.capstone.security.jwt.JwtProvider;
import com.project.capstone.service.JadwalService;
import com.project.capstone.util.ResponseUtil;
import com.project.capstone.constant.AppConstant;

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
//@RequestMapping("/v1/jadwal")
@RequestMapping(value = "/jadwal")
public class JadwalController {

    @Autowired
    private JadwalService jadwalService;
    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllJadwal() {
        return jadwalService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getJadwalById(@PathVariable(value = "id") Long jadwalId) {
        return jadwalService.getJadwalById(jadwalId);
    }
    @PostMapping(value = "")
    public ResponseEntity<Object> createJadwal(@RequestBody JadwalRequest request) {
        return jadwalService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteJadwal(@PathVariable(value = "id") Long jadwalId) {
        return jadwalService.deleteJadwal(jadwalId);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateJadwal(@PathVariable(value = "id") Long jadwalId,
                                              @RequestBody JadwalRequest request) {
        return jadwalService.updateJadwal(request, jadwalId);
    }

    @PutMapping(value = "/editbydokter/{id}")
    @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<Object> updateJadwalByDokter(@PathVariable(value = "id") Long jadwalId,
                                              @RequestBody JadwalRequest request) {
        return jadwalService.updateJadwalByDokter(request, jadwalId);
    }

    // @GetMapping(value = "/jadwalbydokter")
    // @PreAuthorize("hasRole('DOKTER')")
    // public ResponseEntity<?> getJadwalByUser(Principal principal) {
    //     try {
    //         List<Jadwal> jadwal = jadwalService.getJadwalByUser(principal.getName());
    //         return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal, HttpStatus.OK);
    //     } catch (Exception e) {
    //         return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    @GetMapping(value = "/jadwalbydokter")
    @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<?> getJadwalByUser(HttpServletRequest request) {
        try {
            List<Jadwal> jadwal = jadwalService.getJadwalByUser(getId(request));
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, jadwal, HttpStatus.OK);
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

