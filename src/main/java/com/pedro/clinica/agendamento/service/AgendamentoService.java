package com.pedro.clinica.agendamento.service;

import com.pedro.clinica.agendamento.dto.AgendamentoCancelRequestDto;
import com.pedro.clinica.agendamento.dto.AgendamentoCreateRequestDto;
import com.pedro.clinica.agendamento.dto.AgendamentoResponseDto;
import com.pedro.clinica.agendamento.entity.Agendamento;
import com.pedro.clinica.agendamento.enums.StatusAgendamento;
import com.pedro.clinica.agendamento.mapper.AgendamentoMapper;
import com.pedro.clinica.agendamento.repository.AgendamentoRepository;
import com.pedro.clinica.exception.custom.ConflictException;
import com.pedro.clinica.exception.custom.InvalidDateException;
import com.pedro.clinica.exception.custom.ResourceNotFoundException;
import com.pedro.clinica.paciente.entity.Paciente;
import com.pedro.clinica.paciente.service.PacienteService;
import com.pedro.clinica.profissional.entity.Profissional;
import com.pedro.clinica.profissional.service.ProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    private final AgendamentoMapper agendamentoMapper;

    private final PacienteService pacienteService;

    private final ProfissionalService profissionalService;

    // CADASTRAR AGENDAMENTO
    public AgendamentoResponseDto createAgendamento(AgendamentoCreateRequestDto dto){
        Paciente paciente = pacienteService.findEntityById(dto.pacienteId());
        Profissional profissional = profissionalService.findEntityById(dto.profissionalId());

        if (dto.dateTime().isBefore(LocalDateTime.now())){
            throw new InvalidDateException("Data/Hora não pode ser no passado.");
        }

        if (agendamentoRepository.existsByProfissionalAndDateTime(profissional, dto.dateTime())){
            throw new ConflictException("Profissional já possui agendamento neste horário");
        }

        Agendamento agendamento = Agendamento.builder()
                .paciente(paciente)
                .profissional(profissional)
                .dateTime(dto.dateTime())
                .appointmentType(dto.appointmentType())
                .status(StatusAgendamento.AGENDADO)
                .build();

        Agendamento saved = agendamentoRepository.save(agendamento);

        return agendamentoMapper.toDto(saved);
    }

    // CANCELAR AGENDAMENTO
    public AgendamentoResponseDto cancelAgendamento(UUID id, AgendamentoCancelRequestDto dto){
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não existe"));

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO){
            throw new ConflictException("Agendamento já está cancelado.");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);

        agendamento.setCancellationReason(dto.cancellationReason());

        Agendamento saved = agendamentoRepository.save(agendamento);

        return agendamentoMapper.toDto(saved);
    }

    // LISTAR AGENDAMENTOS
    public List<AgendamentoResponseDto> findAll(UUID pacienteId, UUID profissionalId, StatusAgendamento status){
        return agendamentoRepository.findWithFilters(pacienteId, profissionalId, status)
                .stream()
                .map(agendamentoMapper::toDto)
                .toList();
    }
}
