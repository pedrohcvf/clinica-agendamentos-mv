package com.pedro.clinica.agendamento.dto;

import jakarta.validation.constraints.NotBlank;

public record AgendamentoCancelRequestDto(@NotBlank String cancellationReason) {
}
