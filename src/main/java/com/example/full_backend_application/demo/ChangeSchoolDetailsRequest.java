package com.example.full_backend_application.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeSchoolDetailsRequest {
    private String newfaculty;

    private Integer newCgpa;
    private Boolean updateEmployment;
}

