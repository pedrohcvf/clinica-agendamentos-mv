package com.pedro.clinica.paciente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PacienteRequestDto(@NotBlank String name,
                                 @NotNull @Past LocalDate birthDate,
                                 @NotBlank @CPF String cpf,
                                 @NotBlank String phoneNumber) {
}
