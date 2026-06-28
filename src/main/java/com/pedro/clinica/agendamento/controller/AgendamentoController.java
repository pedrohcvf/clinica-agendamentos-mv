package com.pedro.clinica.agendamento.controller;

import com.pedro.clinica.agendamento.dto.AgendamentoCancelRequestDto;
import com.pedro.clinica.agendamento.dto.AgendamentoCreateRequestDto;
import com.pedro.clinica.agendamento.dto.AgendamentoResponseDto;
import com.pedro.clinica.agendamento.enums.StatusAgendamento;
import com.pedro.clinica.agendamento.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    // CADASTRAR AGENDAMENTO
    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> createAgendamento(@Valid @RequestBody AgendamentoCreateRequestDto dto){
        AgendamentoResponseDto agendamento = agendamentoService.createAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    // LISTAR AGENDAMENTOS
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> findAll(
            @RequestParam(required = false) UUID pacienteId,
            @RequestParam(required = false) UUID profissionalId,
            @RequestParam(required = false) StatusAgendamento status){
        List<AgendamentoResponseDto> agendamentoList = agendamentoService.findAll(pacienteId, profissionalId, status);
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoList);
    }

    // CANCELAR AGENDAMENTO
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<AgendamentoResponseDto> cancelAgendamento(
            @PathVariable UUID id,
            @Valid @RequestBody AgendamentoCancelRequestDto dto){
        AgendamentoResponseDto agendamento = agendamentoService.cancelAgendamento(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(agendamento);
    }

}
