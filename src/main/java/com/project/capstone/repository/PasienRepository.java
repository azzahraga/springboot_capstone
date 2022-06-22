package com.project.capstone.repository;

import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Pasien;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface PasienRepository extends SoftDeletesRepository<Pasien, Long> {
    // Optional<Pasien> findOne(Long id);   
}