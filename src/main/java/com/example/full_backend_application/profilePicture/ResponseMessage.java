package com.example.full_backend_application.profilePicture;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@Builder
public class ResponseMessage {
    private String message;

    public ResponseMessage(
            String message) {
        this.message = message;
    }
}
