package com.job.app.jobms.clients;

import com.job.app.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYSERVICE")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable Long id);
}
