package com.example.full_backend_application.registrationToken;

import com.example.full_backend_application.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Builder
@Data
public class ConfirmationTokenService {
    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRespository;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRespository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRespository.findByToken(token);

    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRespository.updateConfirmedAt(token, LocalDateTime.now());
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(()-> new IllegalStateException("Token not found"));
        if (confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already taken");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired");
        }
        setConfirmedAt(token);
        userService.enableAppUser(confirmationToken.getUser().getEmail());
        return "Confirmed";
    }



}

