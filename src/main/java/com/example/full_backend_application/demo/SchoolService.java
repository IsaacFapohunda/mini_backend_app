package com.example.full_backend_application.demo;

import com.example.full_backend_application.user.User;

import com.example.full_backend_application.user.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
@Data

public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;




    public School schoolData(SchoolRequest schoolRequest) throws Exception{

        User user = userRepository.findById(schoolRequest.getUser()).orElseThrow(()-> new Exception("User not found"));

        var school = School.builder()
                .cgpa(schoolRequest.getCgpa())
                .faculty(schoolRequest.getFaculty())
                .isEmployed(schoolRequest.getIsEmployed())
                .user(user)
                .build();
        System.out.println(schoolRepository.save(school));
       return schoolRepository.save(school);
    }

    public School RetrieveSchoolDetail(String id){
        return schoolRepository.findById(id).get();
    }



    public School editSchoolDetails(String id, ChangeSchoolDetailsRequest changeSchoolDetailsRequest){
       School school = schoolRepository.findById(id).get();
       school.setFaculty(changeSchoolDetailsRequest.getNewfaculty());
       school.setCgpa(changeSchoolDetailsRequest.getNewCgpa());
       school.setIsEmployed(changeSchoolDetailsRequest.getUpdateEmployment());
        System.out.println(schoolRepository.save(school));
      return schoolRepository.save(school);

    }

}
