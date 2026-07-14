package com.adx.ReadingLog.controller.dto;

import org.springframework.web.multipart.MultipartFile;

public record BookRequestDTO(
        String title,
        String description,
        String author,
      //  MultipartFile image,
        boolean completed
) {}
