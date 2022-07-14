package com.project.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Dokter;

@Repository
public interface DokterRepository extends JpaRepository<Dokter, Long> {
    @Query(value = "SELECT d FROM Dokter d WHERE d.user.id= ?1")
    List<Dokter> findDokterByUser(Long id);

    @Query(value = "SELECT d FROM Dokter d WHERE d.srp= ?1")
    Dokter findDokterBySrp(Long srp);
}