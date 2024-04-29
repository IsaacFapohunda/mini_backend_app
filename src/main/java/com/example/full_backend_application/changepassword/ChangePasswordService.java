package com.example.full_backend_application.changepassword;

import com.example.full_backend_application.user.User;
import com.example.full_backend_application.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String ChangePassword(ChangePasswordRequest changePasswordRequest, Principal connectedUser){

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())){
            throw  new IllegalStateException("Wrong password");
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
        System.out.println(user.getPassword());
        return "Password Successfully Updated";
    }
}
