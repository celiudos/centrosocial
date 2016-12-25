package org.iaff.csiaff.service;

import java.util.Optional;

import org.iaff.csiaff.model.Cidade;
import org.iaff.csiaff.repository.Cidades;
import org.iaff.csiaff.service.exception.NomeCidadeJaCadastradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CadastroCidadeService {

	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (cidadeExistente.isPresent()) {
			throw new NomeCidadeJaCadastradaException("Nome de cidade já cadastrado");
		}
		
		cidades.save(cidade);
	}

	
}
