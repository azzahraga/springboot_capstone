package com.project.capstone.domain.dao;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.capstone.domain.dao.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@SQLDelete(sql = "UPDATE M_JADWAL SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Jadwal extends BaseEntity{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_urut", nullable = false)
    private Integer nourut;

    @Column(name = "jenis_perawatan", nullable = false)
    private String jp;

    @Column(name = "controll")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate controll;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "diagnosa")
    private String diagnosa;

    @Column(name = "tanggal_kunjungan", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    @ManyToOne
    @JoinColumn (name = "dokter_id", referencedColumnName = "id")
    private Dokter dokter;

    @ManyToOne
    @JoinColumn (name = "pasien_id", referencedColumnName = "id")
    private Pasien pasien;
    
    @JsonIgnore
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;
}
