package com.example.full_backend_application.changepassword;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/full_backend_app/changePasswoord")
@RequiredArgsConstructor
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Principal connectedUser){
        changePasswordService.ChangePassword(changePasswordRequest, connectedUser);
        return ResponseEntity.ok().build();
    }
}
