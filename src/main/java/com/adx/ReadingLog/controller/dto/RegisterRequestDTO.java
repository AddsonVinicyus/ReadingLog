package com.adx.ReadingLog.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "O nome de usuário não pode ser vazio")
        @Size(min = 4, max = 20, message = "O usuário deve ter entre 4 e 20 caracteres")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "A senha deve conter letras e números")
        String password
) {}
