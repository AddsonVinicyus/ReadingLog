package com.adx.ReadingLog.service;


import com.adx.ReadingLog.controller.dto.RegisterRequestDTO;
import com.adx.ReadingLog.controller.dto.UserResponseDTO;
import com.adx.ReadingLog.model.User;
import com.adx.ReadingLog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void register(RegisterRequestDTO userDTO){
        User user = new User(userDTO);
        repository.save(user);
    }

}
