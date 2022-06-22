package com.project.capstone.repository;

import com.project.capstone.domain.dao.User;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SoftDeletesRepository<User, Long> {
    // User getDistinctTopByUsername(String username);
}