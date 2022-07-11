package com.project.capstone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;


@Repository
public interface JadwalRepository extends SoftDeletesRepository<Jadwal, Long> {
    // @Query(value = "SELECT j FROM M_JADWAL j JOIN j.M_DOKTER d WHERE d.id = ?")
    @Query(value = "SELECT j FROM Jadwal j WHERE j.dokter.user.id= ?1")
    List<Jadwal> findJadwalByUser(Long id);
}