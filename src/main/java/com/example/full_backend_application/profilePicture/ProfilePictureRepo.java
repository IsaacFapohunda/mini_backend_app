package com.example.full_backend_application.profilePicture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePictureRepo extends JpaRepository<ProfilePictureModel, String> {
}
