package com.job.app.jobms.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String title;
    private String description;
    private Double rating;
    private Long companyId;
}
