package com.example.full_backend_application.registration;

import com.example.full_backend_application.user.UserRepository;
import lombok.*;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@Builder
@RequestMapping("api/v1/full_backend_app/auth")
@RestController
@RequiredArgsConstructor
public class RegistrationController {


    private final RegistrationService registrationService;


    @PostMapping("/register")
    private ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(registrationService.register(registrationRequest));

    }







}
