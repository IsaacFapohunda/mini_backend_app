package com.example.full_backend_application.profilePicture;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@RestController
@RequestMapping("api/v1/full_backend_app/auth/profilePicture")
public class ProfilePictureController {
    private ProfilePictureRepo profilePictureRepo;
    @Autowired
    private ProfilePictureService profilePictureService;

    @PostMapping
    public ResponseEntity<ResponseMessage> uploadDp(@RequestPart("file")MultipartFile file) {
      try{
          profilePictureService.uploadDp(file);
          String message = "Uploaded the file successfully: " + file.getOriginalFilename();
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      }catch (Exception e){
          String message = "Could not upload the file: " + file.getOriginalFilename() + "!";
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

      }
    }


    @GetMapping
    public ResponseEntity<List<ProfilePictureResponse>> getListPictures(){
        List<ProfilePictureResponse> profilePictures = profilePictureService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/profilePictures/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ProfilePictureResponse(
                    dbFile.getFileName(),
                    fileDownloadUri,
                    dbFile.getContentType(),
                    (long) dbFile.getBytes().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(profilePictures);
    }
}
