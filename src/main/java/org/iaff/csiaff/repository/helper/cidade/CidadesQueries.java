package org.iaff.csiaff.repository.helper.cidade;

import org.iaff.csiaff.model.Cidade;
import org.iaff.csiaff.repository.filter.CidadeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface CidadesQueries {

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
	
}
