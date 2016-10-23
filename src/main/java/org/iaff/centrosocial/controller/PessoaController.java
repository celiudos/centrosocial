package org.iaff.centrosocial.controller;

import java.util.Arrays;
import java.util.List;

import org.iaff.centrosocial.filter.PessoaFilter;
import org.iaff.centrosocial.model.Escolaridade;
import org.iaff.centrosocial.model.EstadoCivil;
import org.iaff.centrosocial.model.Pessoa;
import org.iaff.centrosocial.model.Sexo;
import org.iaff.centrosocial.model.TipoDocIdentidade;
import org.iaff.centrosocial.model.UF;
import org.iaff.centrosocial.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

	private static final String CADASTRO_VIEW = "/pessoa/CadastroPessoa";

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Pessoa());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Pessoa pessoa, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		try {
			pessoaService.salvar(pessoa);
			attributes.addFlashAttribute("mensagem", "Pessoa salva com sucesso!");
			return "redirect:/pessoas/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") PessoaFilter filtro){
		List<Pessoa> todasPessoas = pessoaService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("/pessoa/PesquisaPessoas");
		mv.addObject("pessoas", todasPessoas);
		return mv;
	}
	
	@RequestMapping("{idPessoa}")
	public ModelAndView editar(@PathVariable("idPessoa") Pessoa pessoa){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(pessoa);
		return mv;
	}
	
	@RequestMapping(value="{idPessoa}", method =  RequestMethod.DELETE)
	public String excluir(@PathVariable Long idPessoa, RedirectAttributes attributes){
		pessoaService.excluir(idPessoa);
		
		attributes.addFlashAttribute("mensagem", "Pessoa excluída com sucesso!");
		return "redirect:/pessoas";
	}
	
	@RequestMapping(value = "/{idPessoa}/mudarstatus", method = RequestMethod.PUT)
	public @ResponseBody String mudarstatus(@PathVariable Long idPessoa){
		return pessoaService.mudarstatus(idPessoa);
	}
	
	@ModelAttribute("todasEscolaridades")
	public List<Escolaridade> todasEscolaridades() {
		return Arrays.asList(Escolaridade.values());
	}

	@ModelAttribute("todasUFs")
	public List<UF> todasUFs() {
		return Arrays.asList(UF.values());
	}
	
	@ModelAttribute("todosSexos")
	public List<Sexo> todosSexos() {
		return Arrays.asList(Sexo.values());
	}
	
	@ModelAttribute("todosEstadosCivis")
	public List<EstadoCivil> todosEstadosCivis() {
		return Arrays.asList(EstadoCivil.values());
	}
	
	@ModelAttribute("todosTiposDocIdentidade")
	public List<TipoDocIdentidade> todosTiposDocIdentidade() {
		return Arrays.asList(TipoDocIdentidade.values());
	}
	
	
	
	

}
