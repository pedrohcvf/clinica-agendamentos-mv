package com.pedro.clinica.agendamento.dto;

import com.pedro.clinica.agendamento.enums.StatusAgendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoResponseDto(UUID id,
                                     String pacienteName,
                                     String profissionalName,
                                     LocalDateTime dateTime,
                                     String appointmentType,
                                     StatusAgendamento status,
                                     String cancellationReason) {
}
