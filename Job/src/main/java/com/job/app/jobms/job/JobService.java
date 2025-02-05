package com.job.app.jobms.job;

import com.job.app.jobms.payload.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job getJobById(Long Id);
    JobWithCompanyDTO createJob(Job  job);
    Boolean deleteJobById(Long id);
    Boolean updateJob(Long id ,Job job);
}
