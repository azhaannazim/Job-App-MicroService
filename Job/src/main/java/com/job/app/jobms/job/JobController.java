package com.job.app.jobms.job;

import com.job.app.jobms.payload.JobWithCompanyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return (job != null) ? ResponseEntity.ok(job) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<JobWithCompanyDTO> createJob(@RequestBody Job job) {
        JobWithCompanyDTO jobWithCompanyDTO = jobService.createJob(job);
        return ResponseEntity.ok(jobWithCompanyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job) ?
                ResponseEntity.ok("Job updated successfully") :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return jobService.deleteJobById(id) ?
                ResponseEntity.ok("Job deleted successfully") :
                ResponseEntity.notFound().build();
    }
}
