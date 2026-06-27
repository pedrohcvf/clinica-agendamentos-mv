package com.pedro.clinica.profissional.repository;

import com.pedro.clinica.profissional.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, UUID> {

    boolean existsByCrm(String crm);

    boolean existsByCpf(String cpf);

}
