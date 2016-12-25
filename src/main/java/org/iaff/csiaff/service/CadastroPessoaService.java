package org.iaff.csiaff.service;

import java.util.Optional;

import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.repository.Pessoas;
import org.iaff.csiaff.service.exception.ImpossivelExcluirEntidadeException;
import org.iaff.csiaff.service.exception.NumeroDocumentoJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroPessoaService {

	@Autowired
	private Pessoas pessoas;
	
	@Transactional
	public void salvar(Pessoa pessoa) {
		Optional<Pessoa> pessoaExistente = pessoas.findByNumeroDocumento(pessoa.getNumeroDocumentoSemFormatacao());
		if (pessoaExistente.isPresent() && !pessoaExistente.get().equals(pessoa)) {
			throw new NumeroDocumentoJaCadastradoException("Número de documento já cadastrado");
		}
		
		pessoas.save(pessoa);
	}
	
	@Transactional
	public void excluir(Pessoa pessoa) {
		try {
			pessoas.delete(pessoa);
			pessoas.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir. Pessoa já foi associada a outra entidade.");
		}
	}
	
}
