package com.adx.ReadingLog.controller.dto;

import java.util.UUID;

public record ReadingSessionResponseDTO(
        UUID bookId,
        Integer totalSessions,
        Integer totalDurationSeconds,
        Integer totalPagesRead,
        String lastSession
) {
}
