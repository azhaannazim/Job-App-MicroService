package com.job.app.jobms.payload;

import com.job.app.jobms.external.Company;
import com.job.app.jobms.job.Job;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobWithCompanyDTO {

    private Job job;
    private Company company;
}
