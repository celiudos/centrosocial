package org.iaff.csiaff.repository;

import java.util.List;
import java.util.Optional;

import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.helper.usuario.UsuariosQueries;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmail(String email);

	public List<Usuario> findByCodigoIn(Long[] codigos);

}