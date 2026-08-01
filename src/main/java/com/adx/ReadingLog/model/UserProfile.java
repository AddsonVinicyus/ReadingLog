package com.adx.ReadingLog.model;

import com.adx.ReadingLog.controller.dto.RegisterRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "user_profile")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name_profile", nullable = false, length = 100)
    private String nameProfile;
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public UserProfile(RegisterRequestDTO registerDTO, User user){
        this.nameProfile = registerDTO.nameProfile();
        this.email = registerDTO.email();
        this.user = user;

    }


}
