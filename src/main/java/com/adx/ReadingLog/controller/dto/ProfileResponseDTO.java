package com.adx.ReadingLog.controller.dto;

import com.adx.ReadingLog.model.UserProfile;

import java.util.UUID;

public record ProfileResponseDTO(
        UUID uuid,
        String nameProfile,
        String email
){
    public ProfileResponseDTO(UserProfile profile){
        this(profile.getId(), profile.getNameProfile(), profile.getEmail());
    }
}
