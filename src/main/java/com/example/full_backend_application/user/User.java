package com.example.full_backend_application.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@Table(name = "fba")
@Entity
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy= "uuid2")
    private String Id;
    @Column(unique = true)

    private String firstName;
    private  String lastName;
    private  String email;
    private  String password;
    private Boolean enabled = false;
    private Boolean locked = false;
    private  Boolean isCredentialsNonExpired = true;
    private  Boolean isAccountNonExpired = true;


    @Enumerated(EnumType.STRING)
 private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public User(String id,
                String firstName,
                String lastName,
                String email,
                String password,
                Boolean isCredentialsNonExpired,
                Boolean isAccountNonExpired,
                Role role) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isAccountNonExpired = isAccountNonExpired;
        this.role = role;
    }

    public Map<String, String> userResponse(){
        Map<String, String> response = new HashMap<>();
        response.put("firstname", getFirstName());
        response.put("email", getEmail());
        response.put("lastName", getLastName());

        return response;
    }
}
