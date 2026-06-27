package com.pedro.clinica.paciente.mapper;

import com.pedro.clinica.paciente.dto.PacienteRequestDto;
import com.pedro.clinica.paciente.dto.PacienteResponseDto;
import com.pedro.clinica.paciente.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    // CONVERSÃO PARA DTO
    public PacienteResponseDto toDto(Paciente paciente){
        return new PacienteResponseDto(paciente.getId(),
                paciente.getName(),
                paciente.getBirthDate(),
                paciente.getCpf(),
                paciente.getPhoneNumber());
    }

    // CONVERSÃO PARA ENTIDADE
    public Paciente toEntity(PacienteRequestDto dto){
        return Paciente.builder().name(dto.name())
                .birthDate(dto.birthDate())
                .cpf(dto.cpf().replaceAll("[^\\d]", ""))
                .phoneNumber(dto.phoneNumber())
                .build();
    }

}
