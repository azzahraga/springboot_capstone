package com.project.capstone.repository;

import org.springframework.stereotype.Repository;

import com.project.capstone.domain.dao.Review;
import com.project.capstone.repository.softdeletes.SoftDeletesRepository;


@Repository
public interface ReviewRepository extends SoftDeletesRepository<Review, Long> {
    
}