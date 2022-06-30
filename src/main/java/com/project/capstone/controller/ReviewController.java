package com.project.capstone.controller;

import com.project.capstone.domain.dto.ReviewRequest;
import com.project.capstone.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "")
    // @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<Object> getAllReview() {
        return reviewService.getAll();
    }
    @GetMapping(value = "/{id}")
    // @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<Object> getReviewById(@PathVariable(value = "id") Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PostMapping(value = "")
    // @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<Object> createReview(@RequestBody ReviewRequest request) {
        return reviewService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    // @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<Object> deleteReview(@PathVariable(value = "id") Long reviewId) {
        return reviewService.deleteReview(reviewId);
    }
    @PutMapping(value = "/{id}")
    // @PreAuthorize("hasRole('DOKTER')")
    public ResponseEntity<Object> updateReview(@PathVariable(value = "id") Long reviewId,
                                              @RequestBody ReviewRequest request) {
        return reviewService.updateReview(request, reviewId);
    }


    
}