package com.pedro.clinica.paciente.service;

import com.pedro.clinica.exception.custom.ConflictException;
import com.pedro.clinica.exception.custom.ResourceNotFoundException;
import com.pedro.clinica.paciente.dto.PacienteRequestDto;
import com.pedro.clinica.paciente.dto.PacienteResponseDto;
import com.pedro.clinica.paciente.entity.Paciente;
import com.pedro.clinica.paciente.mapper.PacienteMapper;
import com.pedro.clinica.paciente.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteMapper pacienteMapper;

    private final PacienteRepository pacienteRepository;

    // CADASTRAR PACIENTE
    public PacienteResponseDto addPaciente(PacienteRequestDto dto){
        try {
            Paciente paciente = pacienteMapper.toEntity(dto);
            Paciente saved = pacienteRepository.save(paciente);
            return pacienteMapper.toDto(saved);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("CPF Já cadastrado.");
        }

    }

    // LISTAR PACIENTES
    public List<PacienteResponseDto> findAll(){
        return pacienteRepository.findAll()
                .stream()
                .map(paciente -> pacienteMapper.toDto(paciente))
                .toList();
    }

    // LISTAR PACIENTE POR ID
    public PacienteResponseDto findById(UUID id){
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado"));
        return pacienteMapper.toDto(paciente);
    }

}
