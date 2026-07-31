package com.adx.ReadingLog.controller;

import com.adx.ReadingLog.controller.dto.ProfileResponseDTO;
import com.adx.ReadingLog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(service.getProfile(userDetails.getUsername()), HttpStatus.OK);
    }

}
