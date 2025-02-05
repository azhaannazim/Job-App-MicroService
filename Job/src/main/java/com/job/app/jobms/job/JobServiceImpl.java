package com.job.app.jobms.job;

import com.job.app.jobms.external.Company;
import com.job.app.jobms.payload.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public JobWithCompanyDTO createJob(Job job) {

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
    private JobWithCompanyDTO convertToDto(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);

        Company company = restTemplate.getForObject(
                "http://COMPANYSERVICE:8081/companies/" + job.getCompanyId() ,
                Company.class
        );

        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;

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
