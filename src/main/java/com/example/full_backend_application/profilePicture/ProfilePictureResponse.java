package com.example.full_backend_application.profilePicture;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ProfilePictureResponse {
    private String fileName;
    private String url;
    private String contentType;
    private Long size;

    public ProfilePictureResponse(String fileName, String url, String contentType, Long size) {
        this.fileName = fileName;
        this.url = url;
        this.contentType = contentType;
        this.size = size;
    }
}
