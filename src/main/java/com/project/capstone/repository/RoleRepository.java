package com.project.capstone.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Role;
import com.project.capstone.domain.dao.RoleEnum;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;

@Repository
public interface RoleRepository extends SoftDeletesRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}