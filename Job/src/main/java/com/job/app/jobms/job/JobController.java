package com.job.app.jobms.job;

import com.job.app.jobms.payload.JobDTO;
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
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO jobDTO = jobService.getJobById(id);
        return (jobDTO != null) ? ResponseEntity.ok(jobDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody Job job) {
        JobDTO jobDTO = jobService.createJob(job);
        return ResponseEntity.ok(jobDTO);
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
