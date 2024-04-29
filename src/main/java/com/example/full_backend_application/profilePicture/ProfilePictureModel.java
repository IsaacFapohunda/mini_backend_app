package com.example.full_backend_application.profilePicture;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class ProfilePictureModel {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy= "uuid2")
    private String Id;
    @Column(unique = true)

private String fileName;
private String contentType;
    @Lob
    private byte[] bytes;

//    @OneToOne
//    @JoinColumn(
//            nullable = false,
//            name = "user_id"
//    )
//    private User user;

    public ProfilePictureModel(String fileName, String contentType, byte[] bytes) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.bytes = bytes;


    }
}
