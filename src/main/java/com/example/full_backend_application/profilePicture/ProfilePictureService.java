package com.example.full_backend_application.profilePicture;

import com.example.full_backend_application.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@AllArgsConstructor
public class ProfilePictureService {

    private RegistrationRequest registrationRequest;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfilePictureRepo profilePictureRepo;
    public ProfilePictureModel uploadDp(MultipartFile file) throws IOException {

           String fileName = StringUtils.cleanPath(file.getOriginalFilename());
           ProfilePictureModel profilePicture = new ProfilePictureModel(fileName, file.getContentType(), file.getBytes());
           return profilePictureRepo.save(profilePicture);
    }

    public ProfilePictureModel getFile(String Id){
        return profilePictureRepo.findById(Id).get();
    }

    public Stream<ProfilePictureModel> getAllFiles(){
        return profilePictureRepo.findAll().stream();
    }
}
