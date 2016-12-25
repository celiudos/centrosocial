package org.iaff.csiaff.repository;

import java.util.List;
import java.util.Optional;

import org.iaff.csiaff.model.Cidade;
import org.iaff.csiaff.model.Estado;
import org.iaff.csiaff.repository.helper.cidade.CidadesQueries;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
	
}