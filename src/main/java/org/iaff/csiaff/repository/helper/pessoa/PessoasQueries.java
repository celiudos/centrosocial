package org.iaff.csiaff.repository.helper.pessoa;

import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoasQueries {

	public Page<Pessoa> filtrar(PessoaFilter filtro, Pageable pageable);
	
}
