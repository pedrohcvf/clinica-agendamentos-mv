package com.pedro.clinica;

import com.pedro.clinica.agendamento.dto.AgendamentoCancelRequestDto;
import com.pedro.clinica.agendamento.dto.AgendamentoCreateRequestDto;
import com.pedro.clinica.agendamento.entity.Agendamento;
import com.pedro.clinica.agendamento.enums.StatusAgendamento;
import com.pedro.clinica.agendamento.mapper.AgendamentoMapper;
import com.pedro.clinica.agendamento.repository.AgendamentoRepository;
import com.pedro.clinica.agendamento.service.AgendamentoService;
import com.pedro.clinica.exception.custom.ConflictException;
import com.pedro.clinica.exception.custom.InvalidDateException;
import com.pedro.clinica.paciente.entity.Paciente;
import com.pedro.clinica.paciente.service.PacienteService;
import com.pedro.clinica.profissional.entity.Profissional;
import com.pedro.clinica.profissional.service.ProfissionalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @Mock
    private PacienteService pacienteService;

    @Mock
    private ProfissionalService profissionalService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private AgendamentoMapper agendamentoMapper;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Test
    void deveLancarExcecaoQuandoDataNoPassado(){
        AgendamentoCreateRequestDto dto = new AgendamentoCreateRequestDto(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2020, 1, 1, 10, 0),
                "Consulta"
        );

        assertThrows(InvalidDateException.class, () -> agendamentoService.createAgendamento(dto));
    }

    @Test
    void deveLancarExcecaoQuandoProfissionalJaPossuiAgendamentoNoMesmoHorario(){
        UUID profissionalId = UUID.randomUUID();
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);

        AgendamentoCreateRequestDto dto = new AgendamentoCreateRequestDto(
                UUID.randomUUID(),
                profissionalId,
                dateTime,
                "Consulta"
        );

        Profissional profissional = new Profissional();

        when(pacienteService.findEntityById(dto.pacienteId())).thenReturn(new Paciente());
        when(profissionalService.findEntityById(dto.profissionalId())).thenReturn(profissional);
        when(agendamentoRepository.existsByProfissionalAndDateTime(profissional, dateTime)).thenReturn(true);

        assertThrows(ConflictException.class, () -> agendamentoService.createAgendamento(dto));
    }

    @Test
    void deveLancarExcecaoQuandoAgendamentoJaEstaCancelado(){
        UUID id = UUID.randomUUID();

        Agendamento agendamento = new Agendamento();
        agendamento.setStatus(StatusAgendamento.CANCELADO);

        AgendamentoCancelRequestDto dto = new AgendamentoCancelRequestDto("Motivo qualquer");

        when(agendamentoRepository.findById(id)).thenReturn(Optional.of(agendamento));

        assertThrows(ConflictException.class, () -> agendamentoService.cancelAgendamento(id, dto));
    }
}
