package com.job.app.jobms.job;

import com.job.app.jobms.clients.CompanyClient;
import com.job.app.jobms.clients.ReviewClient;
import com.job.app.jobms.external.Company;
import com.job.app.jobms.external.Review;
import com.job.app.jobms.payload.JobDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    JobRepository jobRepository;

    @Autowired
    CompanyClient companyClient;

    @Autowired
    ReviewClient reviewClient;

    @Autowired
    RestTemplate restTemplate;

    int attempt = 0;

    @Override
//    @CircuitBreaker(name = "companyBreaker" ,fallbackMethod = "companyBreakerFallBack")
//    @Retry(name = "companyBreaker" ,fallbackMethod = "companyBreakerFallBack")
    @RateLimiter(name = "companyBreaker" ,fallbackMethod = "companyBreakerFallBack")
    public List<JobDTO> findAll() {
        System.out.println("Attempt :" + ++attempt);
        return jobRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }
    public List<String> companyBreakerFallBack(Exception e){
        List<String> list = new ArrayList<>();
        list.add("dummy");
        return list;
    }

    @Override
    public JobDTO getJobById(Long id) {
        return jobRepository.findById(id)
                .map(this::convertToDto)
                .orElse(new JobDTO());
    }


    @Override
    public JobDTO createJob(Job job) {
        jobRepository.save(job);
        return convertToDto(job);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    private JobDTO convertToDto(Job job){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJob(job);

        //when you know the return datatype , getForObject is ok
        Company company = companyClient.getCompany(job.getCompanyId());

        //when return data type is generic, exchange is more versatile
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;

    }

    @Override
    public Boolean updateJob(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job savedJob = jobOptional.get();
            job.setId(savedJob.getId());
            jobRepository.save(job);

            return true;

        }
        return false;
    }
}
