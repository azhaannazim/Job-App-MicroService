package com.review.reviewms.review;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find a review by reviewId and companyId
    Optional<Review> findByIdAndCompanyId(Long reviewId, Long companyId);

    // Get all reviews for a specific company
    List<Review> findByCompanyId(Long companyId);
}
