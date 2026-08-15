package com.adx.ReadingLog.controller.dto;

import java.time.LocalDateTime;

public record ReadingSessionRequestDTO(
        Integer durationSeconds,
        Integer pagesRead,
        LocalDateTime startTime,
        LocalDateTime endTime
)
{}
