package org.iaff.csiaff.repository.helper.paciente;

import org.iaff.csiaff.model.Paciente;
import org.iaff.csiaff.repository.filter.PacienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacientesQueries {

	public Page<Paciente> filtrar(PacienteFilter filtro, Pageable pageable);
	
}
