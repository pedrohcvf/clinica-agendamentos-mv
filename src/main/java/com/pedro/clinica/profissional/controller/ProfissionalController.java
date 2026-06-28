package com.pedro.clinica.profissional.controller;

import com.pedro.clinica.profissional.dto.ProfissionalRequestDto;
import com.pedro.clinica.profissional.dto.ProfissionalResponseDto;
import com.pedro.clinica.profissional.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    // CADASTRAR PROFISSIONAL
    @PostMapping
    public ResponseEntity<ProfissionalResponseDto> addProfissional(@Valid @RequestBody ProfissionalRequestDto dto){
        ProfissionalResponseDto profissional = profissionalService.addProfissional(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissional);
    }

    // LISTAR PROFISSIONAIS
    @GetMapping
    public ResponseEntity<List<ProfissionalResponseDto>> findAll(){
        List<ProfissionalResponseDto> profissionalList = profissionalService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(profissionalList);
    }

}
