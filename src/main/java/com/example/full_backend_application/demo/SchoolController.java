package com.example.full_backend_application.demo;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/full_backend_app/schoolDetails")
public class SchoolController {

    private final SchoolService schoolService;


    @PostMapping()
    public ResponseEntity<SchoolResponse> saveSchoolData(@RequestBody SchoolRequest schoolRequest){
        try{
            schoolService.schoolData(schoolRequest);
            String message = "School details saved successfully";
            return ResponseEntity.status(HttpStatus.OK).body(new SchoolResponse(message));
        } catch(Exception e){
            String message = "Unable to save school details";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new SchoolResponse(message));
        }

    }

    @GetMapping("/getDetails")
    public ResponseEntity<Map<String, Map<String, String>>> getSchoolDetails(@RequestParam String school_id){
        School school = schoolService.RetrieveSchoolDetail(school_id);
        System.out.println(school);

        Map<String, Map<String, String>> res = new HashMap<>();
        res.put("school", school.schoolResponse());
        res.put("user", school.getUser().userResponse());

        return ResponseEntity.ok(res);
    }


    @GetMapping("/edit")
    public ResponseEntity<Map<String, Map<String, String>>> setNewSchoolData(@RequestParam String school_id, @RequestBody ChangeSchoolDetailsRequest changeSchoolDetailsRequest){
        School school = schoolService.editSchoolDetails(school_id, changeSchoolDetailsRequest);
        System.out.println(school);
        Map<String, Map<String, String>> res = new HashMap<>();
        res.put("school", school.schoolResponse());
        res.put("user", school.getUser().userResponse());

        return ResponseEntity.ok(res);

    }



}



