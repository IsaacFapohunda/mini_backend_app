package com.example.full_backend_application.authentication;


import com.example.full_backend_application.security.JwtService;
import com.example.full_backend_application.user.UserRepository;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try{
            System.out.println(authenticationRequest.getEmail() + "    "+ authenticationRequest.getPassword());
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


      try{
          var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
          if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())){
              return AuthenticationResponse.builder()
                      .token("Invalid username or password")
                      .build();
          }
          var jwtToken = jwtService.generateToken(user);
          return AuthenticationResponse.builder()
                  .token(jwtToken)
                  .build();
      } catch (Exception e){
          return  AuthenticationResponse.builder()
                  .token("Bad request")
                  .build();
      }


    }
}
