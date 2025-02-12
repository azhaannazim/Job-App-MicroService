package com.job.app.jobms.payload;

import com.job.app.jobms.external.Company;
import com.job.app.jobms.external.Review;
import com.job.app.jobms.job.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    private Job job;
    private Company company;
    private List<Review> reviews;
}
