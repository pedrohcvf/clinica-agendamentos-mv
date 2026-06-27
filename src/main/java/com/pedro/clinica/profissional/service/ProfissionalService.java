package com.pedro.clinica.profissional.service;

import com.pedro.clinica.exception.custom.ConflictException;
import com.pedro.clinica.exception.custom.ResourceNotFoundException;
import com.pedro.clinica.profissional.dto.ProfissionalRequestDto;
import com.pedro.clinica.profissional.dto.ProfissionalResponseDto;
import com.pedro.clinica.profissional.entity.Profissional;
import com.pedro.clinica.profissional.mapper.ProfissionalMapper;
import com.pedro.clinica.profissional.repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    private final ProfissionalMapper profissionalMapper;

    // CADASTRAR PROFISSIONAL
    public ProfissionalResponseDto addProfissional(ProfissionalRequestDto dto){
        Profissional profissional = profissionalMapper.toEntity(dto);
        if (profissionalRepository.existsByCpf(profissional.getCpf())){
            throw new ConflictException("CPF já cadastrado.");
        }
        if (profissionalRepository.existsByCrm(profissional.getCrm())){
            throw new ConflictException("CRM já cadastrado.");
        }
        return profissionalMapper.toDto(profissionalRepository.save(profissional));
    }

    // LISTAR PROFISSIONAIS
    public List<ProfissionalResponseDto> findAll(){
        return profissionalRepository.findAll()
                .stream()
                .map(profissionalMapper::toDto)
                .toList();
    }

    // LISTAR PROFISSIONAL POR ID
    public ProfissionalResponseDto findById(UUID id){
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
        return profissionalMapper.toDto(profissional);
    }
}
