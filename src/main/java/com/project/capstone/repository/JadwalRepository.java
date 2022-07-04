package com.project.capstone.repository;

import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;


@Repository
public interface JadwalRepository extends SoftDeletesRepository<Jadwal, Long> {
    
}