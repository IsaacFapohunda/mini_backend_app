package com.example.full_backend_application.registrationToken;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Data
@Builder
@RequestMapping("api/v1/full_backend_app/auth")
@RestController
@RequiredArgsConstructor
public class ConfirmationTokenController {

    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("confirm")
    public String confirm(@RequestParam String token){
        System.out.println(token);
        return confirmationTokenService.confirmToken(token);
    }





}
