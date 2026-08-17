package com.adx.ReadingLog.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReadingSessionRequestDTO(
        Integer durationSeconds,
        Integer pagesRead,
        LocalDateTime startTime,
        LocalDateTime endTime
)
{}
