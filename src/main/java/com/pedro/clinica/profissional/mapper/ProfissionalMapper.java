package com.pedro.clinica.profissional.mapper;
import com.pedro.clinica.profissional.dto.ProfissionalRequestDto;
import com.pedro.clinica.profissional.dto.ProfissionalResponseDto;
import com.pedro.clinica.profissional.entity.Profissional;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {

    // CONVERSÃO PARA DTO
    public ProfissionalResponseDto toDto(Profissional profissional){
        return new ProfissionalResponseDto(profissional.getId(),
                profissional.getName(),
                profissional.getCpf(),
                profissional.getEmail(),
                profissional.getSpecialty(),
                profissional.getCrm(),
                profissional.getPhoneNumber());
    }

    // CONVERSÃO PARA ENTIDADE
    public Profissional toEntity(ProfissionalRequestDto dto){
        return Profissional.builder().name(dto.name())
                .cpf(dto.cpf().replaceAll("[^\\d]", ""))
                .email(dto.email())
                .specialty(dto.specialty())
                .crm(dto.crm())
                .phoneNumber(dto.phoneNumber())
                .build();
    }
}
