/**
 * 
 */
package br.com.jadson.integrationtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.jadson.integrationtest.exception.ContatoException;
import br.com.jadson.integrationtest.model.Contato;
import br.com.jadson.integrationtest.service.ContatoService;

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
public class AgendaController {

	@Autowired
	private ContatoService contatoService;

	public void inserirRegistro(String nome, String ddd, String telefone) throws ContatoException {
		Contato contato = new Contato(nome, ddd, telefone);
		contatoService.inserir(contato);
	}

	public void removerRegistro(Long id) {
		contatoService.remover(id);
	}
	
}
