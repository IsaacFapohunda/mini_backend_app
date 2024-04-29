package com.example.full_backend_application.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SchoolRequest {
    private String faculty;
    private Integer cgpa;
    private Boolean isEmployed;
    private String user;

}