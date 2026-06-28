package com.pedro.clinica.agendamento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoCreateRequestDto(@NotNull UUID pacienteId,
                                          @NotNull UUID profissionalId,
                                          @NotNull LocalDateTime dateTime,
                                          @NotBlank String appointmentType) {
}
