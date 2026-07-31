package com.adx.ReadingLog.service;


import com.adx.ReadingLog.controller.dto.LoginRequestDTO;
import com.adx.ReadingLog.controller.dto.RegisterRequestDTO;
import com.adx.ReadingLog.controller.dto.UserResponseDTO;
import com.adx.ReadingLog.exceptions.UserAlreadyExistsException;
import com.adx.ReadingLog.model.User;
import com.adx.ReadingLog.model.UserProfile;
import com.adx.ReadingLog.repository.ProfileRepository;
import com.adx.ReadingLog.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void register(RegisterRequestDTO userDTO){
        verifyUsername(userDTO.username());
        verifyEmail(userDTO.email());
        User user = new User(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));

        UserProfile profile = new UserProfile(userDTO, user);

        user.setProfile(profile);

        repository.save(user);
    }

    public String verify(LoginRequestDTO request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(request.username());
        return "fail";
    }

    private void verifyUsername(String username){
        if(repository.findByUsername(username) != null)
            throw new UserAlreadyExistsException();
    }

    private void verifyEmail(String email){
        if(profileRepository.findByEmail(email) != null){
            throw new UserAlreadyExistsException("Email já cadastrado.");
        }
    }

}
