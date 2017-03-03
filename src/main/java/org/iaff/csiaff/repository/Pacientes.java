package org.iaff.csiaff.repository;

import java.util.Optional;

import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.repository.helper.paciente.PacientesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pacientes extends JpaRepository<Paciente, Long>, PacientesQueries {

	public Optional<Paciente> findByPessoa(Pessoa pessoa);
}