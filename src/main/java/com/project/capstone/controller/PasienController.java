package com.project.capstone.controller;

import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.domain.dto.PasienRequest;
import com.project.capstone.service.PasienService;

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
//@RequestMapping("/v1/pasien")
@RequestMapping(value = "/pasien")
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAllPasien() {
        return pasienService.getAll();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getPasienById(@PathVariable(value = "id") Long pasienId) {
        return pasienService.getPasienById(pasienId);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createPasien(@RequestBody PasienRequest request) {
        return pasienService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePasien(@PathVariable(value = "id") Long pasienId) {
        return pasienService.deletePasien(pasienId);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updatePasien(@PathVariable(value = "id") Long pasienId,
                                              @RequestBody Pasien request) {
        return pasienService.updatePasien(request, pasienId);
    }


    
}
