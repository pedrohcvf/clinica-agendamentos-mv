package com.pedro.clinica.profissional.dto;

import java.util.UUID;

public record ProfissionalResponseDto(UUID id,
                                      String name,
                                      String cpf,
                                      String email,
                                      String specialty,
                                      String crm,
                                      String phoneNumber) {
}
