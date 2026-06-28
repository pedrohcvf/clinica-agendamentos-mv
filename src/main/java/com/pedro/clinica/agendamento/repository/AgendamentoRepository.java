package com.pedro.clinica.agendamento.repository;

import com.pedro.clinica.agendamento.entity.Agendamento;
import com.pedro.clinica.agendamento.enums.StatusAgendamento;
import com.pedro.clinica.profissional.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

    boolean existsByProfissionalAndDateTime(Profissional profissional, LocalDateTime dateTime);

    @Query("SELECT a FROM Agendamento a WHERE " +
            "(:pacienteId IS NULL OR a.paciente.id = :pacienteId) AND " +
            "(:profissionalId IS NULL OR a.profissional.id = :profissionalId) AND " +
            "(:status IS NULL OR a.status = :status)")
    List<Agendamento> findWithFilters(
            @Param("pacienteId") UUID pacienteId,
            @Param("profissionalId") UUID profissionalId,
            @Param("status") StatusAgendamento status
    );
}
