package com.adx.ReadingLog.controller;

import com.adx.ReadingLog.controller.dto.LoginRequestDTO;
import com.adx.ReadingLog.controller.dto.RegisterRequestDTO;
import com.adx.ReadingLog.controller.dto.UserResponseDTO;
import com.adx.ReadingLog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDTO user){
        service.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(service.verify(request));
    }

}
