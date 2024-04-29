package com.example.full_backend_application.demo;

import com.example.full_backend_application.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class School {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy= "uuid2")
    private String Id;
    @Column(unique = true)
    private  String faculty;
    @Column(nullable = false)
    private Integer cgpa;
    @Column(nullable = false)

    private Boolean isEmployed;


    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    @CreatedDate
    @Column(

            updatable = false
    )
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;

    @CreatedBy
    @Column(
            updatable = false
    )
    private Integer createdBy;



    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;


    public School(String id, String faculty, User user, Integer cgpa, Boolean isEmployed, LocalDateTime createdDate, LocalDateTime lastModified, Integer createdBy, Integer lastModifiedBy) {
        Id = id;
        this.faculty = faculty;
        this.cgpa = cgpa;
        this.isEmployed = isEmployed;
        this.user = user;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public Map<String, String> schoolResponse(){
        Map<String, String> res = new HashMap<>();
        res.put("school_id", getId());
        res.put("cgpa", String.valueOf(getCgpa()));
        res.put("faculty", getFaculty());
        res.put("isEmployed", String.valueOf(getIsEmployed()));
        return res;
    }
}

