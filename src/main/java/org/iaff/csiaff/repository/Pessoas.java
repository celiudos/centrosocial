package org.iaff.csiaff.repository;

import java.util.List;
import java.util.Optional;

import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.repository.helper.pessoa.PessoasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pessoas extends JpaRepository<Pessoa, Long>, PessoasQueries {

	public Optional<Pessoa> findByNumeroDocumento(String numeroDocumento);

	public List<Pessoa> findByNomeStartingWithIgnoreCase(String nome);
	
	public Optional<Pessoa> findByCodigo(Long codigo);

}
