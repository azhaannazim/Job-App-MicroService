package com.job.app.jobms.job;

import com.job.app.jobms.payload.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    JobDTO getJobById(Long Id);
    JobDTO createJob(Job  job);
    Boolean deleteJobById(Long id);
    Boolean updateJob(Long id ,Job job);
}
