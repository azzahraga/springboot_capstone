package com.project.capstone.controller;

import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.domain.dto.JadwalRequest;
import com.project.capstone.service.JadwalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> updateJadwal(@PathVariable(value = "id") Long jadwalId,
                                              @RequestBody Jadwal request) {
        return jadwalService.updateJadwal(request, jadwalId);
    }
    
}

