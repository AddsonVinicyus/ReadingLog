package com.adx.ReadingLog.repository;

import com.adx.ReadingLog.model.ReadingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReadingSessionRepository extends JpaRepository<ReadingSession, UUID> {



}
