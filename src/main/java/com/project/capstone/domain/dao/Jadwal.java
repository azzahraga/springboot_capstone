package com.project.capstone.domain.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.capstone.domain.dao.base.BaseEntityWithDeletedAt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@Table(name = "M_JADWAL")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Jadwal extends BaseEntityWithDeletedAt{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_urut", nullable = false)
    private Integer nourut;

    @Column(name = "jenis_perawatan", nullable = false)
    private String jp;

    @Column(name = "tanggal_kunjungan", nullable = false)
    private Date tanggal;

    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    private User userjadwal;

    @ManyToOne
    @JoinColumn (name = "dokter_id", referencedColumnName = "id")
    private Dokter dokter;

    @ManyToOne
    @JoinColumn (name = "pasien_id", referencedColumnName = "id")
    private Pasien pasien;
    
}
