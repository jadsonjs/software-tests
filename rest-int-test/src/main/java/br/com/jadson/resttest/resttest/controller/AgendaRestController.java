/**
 * 
 */
package br.com.jadson.resttest.resttest.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jadson.resttest.resttest.model.Contato;
import br.com.jadson.resttest.resttest.service.ContatoService;

/**
 * AgendaRestController.java
 *
 * <p REST controller que vai ser testado</p>
 *
 * @author  - 
 * @version 1.0
 * @since 17 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
@RestController
@RequestMapping("/agenda")
public class AgendaRestController {

	@Autowired
	private ContatoService contatoService;

	@GetMapping("/")
	public ResponseEntity<List<Contato>> contatos(){
		List<Contato> contatos = contatoService.buscarContatos();
		return ResponseEntity.ok(contatos);
	}

	@GetMapping("/contato/{id}")
	public ResponseEntity<Contato> contato(@PathVariable Long id){
		return contatoService.buscarContatoOpcional(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(path="/inserir", produces="application/json")
	public ResponseEntity<Contato> inserir(@RequestBody @Valid Contato contato) throws URISyntaxException {
		contato = contatoService.inserirOuAlterar(contato);
		return new ResponseEntity<>(contato, HttpStatus.CREATED);
	}

	@PutMapping(path="/alterar/{id}", produces="application/json")
	public ResponseEntity<Contato> alterar(@PathVariable Long id, @RequestBody @Valid Contato contato) throws URISyntaxException {
		contato = contatoService.inserirOuAlterar(contato);
		return new ResponseEntity<>(contato, HttpStatus.CREATED);
	}

	@DeleteMapping(path="/remover/{id}", produces="application/json")
	public ResponseEntity<Contato> remover(@PathVariable Long id) {
		contatoService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
