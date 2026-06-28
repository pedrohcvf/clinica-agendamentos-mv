package com.pedro.clinica.paciente.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PacienteResponseDto(UUID id,
                                  String name,
                                  LocalDate birthDate,
                                  String cpf,
                                  String phoneNumber) {
}
