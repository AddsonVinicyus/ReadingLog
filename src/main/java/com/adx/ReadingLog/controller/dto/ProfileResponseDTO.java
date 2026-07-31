package com.adx.ReadingLog.controller.dto;

import com.adx.ReadingLog.model.UserProfile;

import java.util.UUID;

public record ProfileResponseDTO(
        UUID uuid,
        String firstName,
        String lastName,
        String email
){
    public ProfileResponseDTO(UserProfile profile){
        this(profile.getId(), profile.getFirstName(), profile.getLastName(), profile.getEmail());
    }
}
