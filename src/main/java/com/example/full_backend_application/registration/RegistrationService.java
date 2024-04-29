package com.example.full_backend_application.registration;

import com.example.full_backend_application.email.EmailSender;
import com.example.full_backend_application.email.EmailService;
import com.example.full_backend_application.registrationEmailValidator.EmailValidator;
import com.example.full_backend_application.registrationToken.ConfirmationToken;
import com.example.full_backend_application.registrationToken.ConfirmationTokenService;
import com.example.full_backend_application.user.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Builder
@Data
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final EmailService emailService;
    private final EmailValidator emailValidator;

    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        boolean isValidEmail = emailValidator.test(registrationRequest.getEmail());
        if (!isValidEmail){
            throw  new IllegalStateException("Email not valid");
        }


            var user = User.builder()
            .firstName(registrationRequest.getFirstName())
            .lastName(registrationRequest.getLastName())
            .email(registrationRequest.getEmail())
            .password(passwordEncoder.encode(registrationRequest.getPassword()))
            .role(Role.USER)
            .build();

    boolean userExits =  userRepository.findByEmail(user.getEmail()).isPresent();

    if (userExits){
        throw new IllegalStateException("Email already taken");
    }

    userRepository.save(user);
        System.out.println(userRepository.save(user));
    String token = UUID.randomUUID().toString();

    ConfirmationToken confirmationToken =new ConfirmationToken(
      token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
    );
    confirmationTokenService.saveConfirmationToken(confirmationToken);
//        String link = "http://localhost:8080/api/v1/full_backend_app/auth/confirm?token="  + token;
//        emailSender.send(
//            registrationRequest.getEmail(),
//            emailService.buildEmail(registrationRequest.getFirstName(), link)
//        );
        return RegistrationResponse.builder()
            .message("Sign in successful")
            .build();
    }

}
