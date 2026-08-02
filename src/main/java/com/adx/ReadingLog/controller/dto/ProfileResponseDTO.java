package com.adx.ReadingLog.controller.dto;

import com.adx.ReadingLog.model.UserProfile;

import java.util.List;
import java.util.UUID;

public record ProfileResponseDTO(
        UUID uuid,
        String nameProfile,
        String email,
        String username,
        int totalBooks,
        int completedBooksCount,
        List<BookSummaryDTO> completedBooksList
){
//    public ProfileResponseDTO(UserProfile profile){
//        this(profile.getId(), profile.getNameProfile(), profile.getEmail(), profile.getUser().getUsername());
//    }
}
