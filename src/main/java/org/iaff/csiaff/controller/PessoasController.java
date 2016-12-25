package org.iaff.csiaff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.iaff.csiaff.controller.page.PageWrapper;
import org.iaff.csiaff.model.Escolaridade;
import org.iaff.csiaff.model.EstadoCivil;
import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.model.Sexo;
import org.iaff.csiaff.model.TipoDocumento;
import org.iaff.csiaff.repository.Pessoas;
import org.iaff.csiaff.repository.Estados;
import org.iaff.csiaff.repository.filter.PessoaFilter;
import org.iaff.csiaff.service.CadastroPessoaService;
import org.iaff.csiaff.service.exception.NumeroDocumentoJaCadastradoException;
import org.iaff.csiaff.service.exception.ImpossivelExcluirEntidadeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {

	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@Autowired
	private Pessoas pessoas;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Pessoa pessoa) {
		ModelAndView mv = new ModelAndView("pessoa/CadastroPessoa");
		mv.addObject("tiposDocumentos", TipoDocumento.values());
		mv.addObject("estados", estados.findAll());
		mv.addObject("todosSexos", Sexo.values());
		mv.addObject("todasEscolaridades", Escolaridade.values());
		mv.addObject("todosEstadosCivis", EstadoCivil.values());
		return mv;
	}
	
	@PostMapping({ "/nova", "{\\+d}" })
	public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(pessoa);
		}
		
		try {
			cadastroPessoaService.salvar(pessoa);
		} catch (NumeroDocumentoJaCadastradoException e) {
			result.rejectValue("numeroDocumento", e.getMessage(), e.getMessage());
			return nova(pessoa);
		}
		
		attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
		return new ModelAndView("redirect:/pessoas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(PessoaFilter pessoaFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pessoa/PesquisaPessoas");
		//mv.addObject("tiposDocumentos", TipoDocumento.values());
		
		PageWrapper<Pessoa> paginaWrapper = new PageWrapper<>(pessoas.filtrar(pessoaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Pessoa pessoa = pessoas.findByCodigo(codigo).get();
		
		// http://blog.caelum.com.br/entendendo-o-lazy-e-o-eager-load-da-jpa/
		
		if(pessoa.getEndereco().getCidade() != null){
			pessoa.getEndereco().setEstado(	pessoa.getEndereco().getCidade().getEstado() );
		}
		
		if(pessoa.getNaturalidade() != null){
			pessoa.setEstadoNascimento(	pessoa.getNaturalidade().getEstado() );
		}
		
		ModelAndView mv = nova(pessoa);
		mv.addObject(pessoa);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Pessoa pessoa) {
		try {
			cadastroPessoaService.excluir(pessoa);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	// pesquisa rapida
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Pessoa> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return pessoas.findByNomeStartingWithIgnoreCase(nome);
	}
	
	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
}
