package com.project.capstone.service;

import java.util.Optional;

import com.project.capstone.constant.AppConstant;
import com.project.capstone.domain.dao.Jadwal;
import com.project.capstone.domain.dao.Review;
import com.project.capstone.domain.dto.ReviewRequest;
import com.project.capstone.repository.JadwalRepository;
import com.project.capstone.repository.ReviewRepository;
import com.project.capstone.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ResponseEntity<Object> getAll() {
        log.info("Get all review");
        // return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, reviewRepository.findAll(), HttpStatus.OK);
        return ResponseEntity.ok().body(reviewRepository.findAll());
    }

    public ResponseEntity<Object> save(ReviewRequest request) {
      try { 
        
        Jadwal jadwal = jadwalRepository.findById(request.getJadwalId())
            .orElseThrow(()-> new Exception("Jadwal Id "+ request.getJadwalId() + "Not Found"));

        log.info("Save new review: {}", request);
        Review review = Review.builder()
            .jadwal(jadwal)
            .catatan(request.getCatatan())
            .diagnosa(request.getDiagnosa())
            .build();
        
            review = reviewRepository.save(review);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, review, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getReview(Long reviewId) {
        return ResponseEntity.ok().body(reviewRepository.findById(reviewId));
    }

    public ResponseEntity<Object> getReviewById(Long id) {
        log.info("Find review detail by review id: {}",id);
        Optional<Review> review = reviewRepository.findOne(id);
        if (review.isEmpty()) return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, review.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteReview(Long id) {
        log.info("Find Review by review id for delete: {}", id);
        try {
            reviewRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateReview(ReviewRequest request, Long id) {
        try {
            log.info("Update user: {}", request);
            Optional<Review> review = reviewRepository.findOne(id);

            Jadwal jadwal = jadwalRepository.findById(request.getJadwalId())
            .orElseThrow(()-> new Exception("Pasien Id "+ request.getJadwalId() + "Not Found"));

            if (review.isEmpty()) {
                log.info("user not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            review.get().setJadwal(jadwal);
            review.get().setCatatan(request.getCatatan());
            review.get().setDiagnosa(request.getDiagnosa());
            
            reviewRepository.save(review.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, review.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update category, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}