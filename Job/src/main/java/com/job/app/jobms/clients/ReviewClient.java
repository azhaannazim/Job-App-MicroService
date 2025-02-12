package com.job.app.jobms.clients;

import com.job.app.jobms.external.Company;
import com.job.app.jobms.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWSERVICE")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam Long companyId);
}
