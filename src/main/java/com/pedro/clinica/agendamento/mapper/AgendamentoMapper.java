package com.pedro.clinica.agendamento.mapper;

import com.pedro.clinica.agendamento.dto.AgendamentoResponseDto;
import com.pedro.clinica.agendamento.entity.Agendamento;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {

    // CONVERSÃO PARA DTO
    public AgendamentoResponseDto toDto(Agendamento agendamento){
        return new AgendamentoResponseDto(agendamento.getId(),
                agendamento.getPaciente().getName(),
                agendamento.getProfissional().getName(),
                agendamento.getDateTime(),
                agendamento.getAppointmentType(),
                agendamento.getStatus(),
                agendamento.getCancellationReason());
    }
}
