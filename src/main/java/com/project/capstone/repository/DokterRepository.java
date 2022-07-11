package com.project.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Dokter;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface DokterRepository extends SoftDeletesRepository<Dokter, Long> {
    @Query(value = "SELECT d FROM Dokter d WHERE d.user.id= ?1")
    List<Dokter> findDokterByUser(Long id);
}