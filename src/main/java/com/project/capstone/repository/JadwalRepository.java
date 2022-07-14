package com.project.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Jadwal;


@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Long> {
    // @Query(value = "SELECT j FROM M_JADWAL j JOIN j.M_DOKTER d WHERE d.id = ?")
    @Query(value = "SELECT j FROM Jadwal j WHERE j.dokter.user.id= ?1")
    List<Jadwal> findJadwalByUser(Long id);
}