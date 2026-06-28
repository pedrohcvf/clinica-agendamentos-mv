package com.pedro.clinica.agendamento.entity;

import com.pedro.clinica.agendamento.enums.StatusAgendamento;
import com.pedro.clinica.paciente.entity.Paciente;
import com.pedro.clinica.profissional.entity.Profissional;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String appointmentType;

    private String cancellationReason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status;

}
