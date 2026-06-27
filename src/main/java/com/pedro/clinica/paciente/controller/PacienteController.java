package com.pedro.clinica.paciente.controller;

import com.pedro.clinica.paciente.dto.PacienteRequestDto;
import com.pedro.clinica.paciente.dto.PacienteResponseDto;
import com.pedro.clinica.paciente.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    // CADASTRAR PACIENTE
    @PostMapping
    public ResponseEntity<PacienteResponseDto> addPaciente(@Valid @RequestBody PacienteRequestDto dto){
        PacienteResponseDto paciente = pacienteService.addPaciente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    // LISTAR PACIENTES
    @GetMapping
    public ResponseEntity<List<PacienteResponseDto>> findAll(){
        List<PacienteResponseDto> pacienteList = pacienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pacienteList);
    }
}
