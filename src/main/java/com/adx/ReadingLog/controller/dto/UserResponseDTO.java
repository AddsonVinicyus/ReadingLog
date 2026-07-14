package com.adx.ReadingLog.controller.dto;

import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String username
){}
