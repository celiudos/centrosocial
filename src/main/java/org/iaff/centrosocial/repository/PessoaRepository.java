package org.iaff.centrosocial.repository;

import java.util.List;

import org.iaff.centrosocial.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	public List<Pessoa> findByNomeContaining(String nome);
}
