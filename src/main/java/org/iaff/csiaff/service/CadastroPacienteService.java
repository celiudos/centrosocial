package org.iaff.csiaff.service;

import java.util.Optional;

import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.repository.Pacientes;
import org.iaff.csiaff.service.exception.ImpossivelExcluirEntidadeException;
import org.iaff.csiaff.service.exception.PacientePessoaJaCadastradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroPacienteService {

	@Autowired
	private Pacientes pacientes;

	@Transactional
	public void salvar(Paciente paciente) {
		// https://dzone.com/articles/java-optional-objects
		Optional<Paciente> pacienteExistente = pacientes.findByPessoa(paciente.getPessoa());

		if (pacienteExistente.isPresent() && paciente.isNovo()) {
			throw new PacientePessoaJaCadastradaException("Paciente já cadastrado");
		}

		pacientes.save(paciente);

	}

	@Transactional
	public void excluir(Paciente paciente) {
		try {
			pacientes.delete(paciente);
			pacientes.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelExcluirEntidadeException(
					"Impossível excluir. Paciente já foi associado a outra entidade.");
		}
	}
	 

}
