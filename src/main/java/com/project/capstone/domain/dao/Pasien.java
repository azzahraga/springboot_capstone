package com.project.capstone.domain.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "M_PASIEN")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Pasien extends BaseEntityWithDeletedAt{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_pasien", nullable = false)
    private String namapasien;

    @Column(name = "nik", nullable = false)
    private String nik;

    @Column(name = "umur", nullable = false)
    private Integer umur;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jeniskelamin;

    @Column(name = "telepon", nullable = false)
    private String telp;

    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    private User userpasien;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pasien")
    private List<Jadwal> jadwal;

    
}
