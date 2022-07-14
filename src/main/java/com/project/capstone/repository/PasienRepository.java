package com.project.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Pasien;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, Long> {
    @Query(value = "SELECT p FROM Pasien p WHERE p.nik= ?1")
    Pasien findPasienByNik(Long nik);

    @Query(value = "SELECT p FROM Pasien p WHERE p.telp= ?1")
    Pasien findPasienBytelp(Long telp);

}