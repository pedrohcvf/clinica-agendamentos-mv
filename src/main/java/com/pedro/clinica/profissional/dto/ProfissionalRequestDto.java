package com.pedro.clinica.profissional.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ProfissionalRequestDto(@NotBlank String name,
                                     @NotBlank @CPF String cpf,
                                     @NotBlank @Email String email,
                                     @NotBlank String specialty,
                                     @NotBlank String crm,
                                     @NotBlank String phoneNumber) {
}
