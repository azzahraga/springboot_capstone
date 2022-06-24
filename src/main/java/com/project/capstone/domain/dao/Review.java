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
import javax.persistence.OneToOne;
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
@Table(name = "M_REVIEW")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Review extends BaseEntityWithDeletedAt{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "catatan", nullable = false)
    private String catatan;

    @Column(name = "diagnosa", nullable = false)
    private String diagnosa;
    
    // @OneToOne(mappedBy="review")
    // private Jadwal jadwal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "jadwal_id", referencedColumnName = "id")
    private Jadwal jadwal;

    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "jadwal_id", nullable = false)
    // private Jadwal jadwal;

}