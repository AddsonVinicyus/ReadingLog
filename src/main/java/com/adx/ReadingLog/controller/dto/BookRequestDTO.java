package com.adx.ReadingLog.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record BookRequestDTO(
        @NotBlank(message = "O nome do livro não pode ser vazio")
        String title,
        String description,
        String author,
        MultipartFile image,
        boolean completed
) {}
