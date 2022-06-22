package com.project.capstone.domain.dao;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.capstone.domain.dao.base.BaseEntityWithDeletedAt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Builder
@Entity
@Table(name = "M_USER")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User extends BaseEntityWithDeletedAt{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // @Column(name = "role", nullable = false)
    // private String role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userdokter")
    private List<Dokter> dokter;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userjadwal")
    private List<Jadwal> jadwal;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userpasien")
    private List<Pasien> pasien;


}
//     public class User extends BaseEntityWithDeletedAt implements UserDetails{
    
//     private static final long serialVersionUID = 6251745099915144658L;

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "username", nullable = false)
//     private String username;

//     @Column(name = "password", nullable = false)
//     private String password;

//     @JsonIgnore
//     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userdokter")
//     private List<Dokter> dokter;

//     @JsonIgnore
//     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userjadwal")
//     private List<Jadwal> jadwal;

//     @JsonIgnore
//     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userpasien")
//     private List<Pasien> pasien;

//     @Column(columnDefinition = "boolean default true")
//     private boolean active = true;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return null;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return this.active;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return this.active;
//     }
//     @Override
//     public boolean isCredentialsNonExpired() {
//         return this.active;
//     }

//     @Override
//     public boolean isEnabled() {
//         return this.active;
//     }    

//     public void setUsername(Object username2) {
//     }
//     // public void setPassword(String encode) {
//     // }
// }
