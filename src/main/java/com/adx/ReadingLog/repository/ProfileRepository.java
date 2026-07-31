package com.adx.ReadingLog.repository;

import com.adx.ReadingLog.model.User;
import com.adx.ReadingLog.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, UUID> {

    UserProfile findByUser(User user);
    UserProfile findByEmail(String email);

}
