package com.adx.ReadingLog.model;

import com.adx.ReadingLog.controller.dto.RegisterRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "users")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;

    public User(RegisterRequestDTO userDTO){
        this.username = userDTO.username();
        this.password = userDTO.password();
    }

}
