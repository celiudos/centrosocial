package org.iaff.centrosocial.service;

import java.util.List;

import org.iaff.centrosocial.filter.PessoaFilter;
import org.iaff.centrosocial.model.Pessoa;
import org.iaff.centrosocial.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoas;

	public void salvar(Pessoa pessoa) {
		try {
			pessoas.save(pessoa);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	public void excluir(Long idPessoa){
		pessoas.delete(idPessoa);
	}

	public List<Pessoa> filtrar(PessoaFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return pessoas.findByNomeContaining(nome);
	}
	
	public String mudarstatus(Long idPessoa){
		Pessoa pessoa = pessoas.findOne(idPessoa);
		pessoa.setCompleto(true);
		pessoas.save(pessoa);
		
		return "Completo";
	}

}
