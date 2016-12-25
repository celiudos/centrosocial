package org.iaff.csiaff.service;

import java.util.Optional;

import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.Usuarios;
import org.iaff.csiaff.service.exception.EmailUsuarioJaCadastradoException;
import org.iaff.csiaff.service.exception.ImpossivelExcluirEntidadeException;
import org.iaff.csiaff.service.exception.SenhaObrigatoriaUsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		if(usuario.getPessoa().getCodigo() == null){
			usuario.setPessoa(null);
		}
		
		usuarios.save(usuario);
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		try {
			usuarios.delete(usuario);
			usuarios.flush();
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir. Usuário já foi associado a outra entidade.");
		}
	}
	
}

