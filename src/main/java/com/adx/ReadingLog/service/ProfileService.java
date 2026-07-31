package com.adx.ReadingLog.service;

import com.adx.ReadingLog.controller.dto.ProfileResponseDTO;
import com.adx.ReadingLog.model.User;
import com.adx.ReadingLog.model.UserProfile;
import com.adx.ReadingLog.repository.ProfileRepository;
import com.adx.ReadingLog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileResponseDTO getProfile(String username){
        try{
            User user = getUserByUsername(username);
            UserProfile profile = profileRepository.findByUser(user);

            return new ProfileResponseDTO(profile);

        } catch (Exception e){
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    private User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
