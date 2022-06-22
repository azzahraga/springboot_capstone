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
@Table(name = "M_DOKTER")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Dokter extends BaseEntityWithDeletedAt{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_dokter", nullable = false)
    private String namadokter;

    @Column(name = "spesialis", nullable = false)
    private String spesialis;

    @Column(name = "srp", nullable = false)
    private String srp;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jeniskelamin;

    @Column(name = "telepon", nullable = false)
    private String telp;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dokter")
    private List<Jadwal> jadwal;
    
    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    private User userdokter;

}
