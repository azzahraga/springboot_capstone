package com.project.capstone.controller;

import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.domain.dto.DokterRequest;
import com.project.capstone.service.DokterService;

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
//@RequestMapping("/v1/dokter")
@RequestMapping(value = "/dokter")
public class DokterController {

    @Autowired
    private DokterService dokterService;

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
                                              @RequestBody Dokter request) {
        return dokterService.updateDokter(request, dokterId);
    }
    
}
