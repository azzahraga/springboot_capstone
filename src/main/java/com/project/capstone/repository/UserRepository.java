package com.project.capstone.repository;

import com.project.capstone.domain.dao.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    
    @Query(value = "SELECT u FROM User u WHERE u.username = ?1")
    User findUsername(String username);
}