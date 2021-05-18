/**
 * 
 */
package br.com.jadson.mvctest.mvctest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jadson.mvctest.mvctest.model.Contato;
import br.com.jadson.mvctest.mvctest.service.ContatoService;

/**
 * AgendaController.java
 *
 * <p></p>
 *
 * @author  - 
 * @version 1.0
 * @since 16 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
@Controller
@RequestMapping("/agenda")
public class AgendaController {
	
	@Autowired
	private ContatoService contatoService;

	@GetMapping("/")
	public ModelAndView getContatos(ModelAndView mav){
		mav.addObject("contatos",contatoService.buscarContatos());
		return mav;
	}

	@GetMapping("/contato/{id}")
	public ModelAndView getContato(@PathVariable("id") Long id,ModelAndView mav){
		mav.setViewName("agenda/contato");
		mav.addObject("contato",contatoService.buscarContato(id));
		return mav;
	}

	@DeleteMapping("/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("successMessage", "Contato removido com sucesso");
		contatoService.remover(id);
		return new ModelAndView("redirect:agenda/");
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro(ModelAndView mav) {
		mav.addObject("contato", new Contato());
		return mav;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Contato contato,BindingResult bindingResult, ModelAndView mav) {
		if (!bindingResult.hasErrors()) {
			mav.addObject("successMessage", "Contato cadastrado com sucesso");
			contatoService.inserir(contato);
		}
		return mav;
	}

}
