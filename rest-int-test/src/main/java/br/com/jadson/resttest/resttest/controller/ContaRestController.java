/**
 * 
 */
package br.com.jadson.resttest.resttest.controller;

import br.com.jadson.resttest.resttest.exception.NegocioException;
import br.com.jadson.resttest.resttest.model.Conta;
import br.com.jadson.resttest.resttest.repository.ContaRepository;
import br.com.jadson.resttest.resttest.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ContaRestController.java
 */
@RestController
@RequestMapping("/conta")
public class ContaRestController {

	@Autowired
	private ContaService contaService;

	@Autowired
	private ContaRepository contaRepository;

	@GetMapping("/")
	public ResponseEntity<List<Conta>> contas(){
		List<Conta> contatos = contaRepository.findAll();
		return ResponseEntity.ok(contatos);
	}

	@GetMapping("/conta/{id}")
	public ResponseEntity<Conta> conta(@PathVariable Long id){
		return contaRepository.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(path="/abrir", produces="application/json")
	public ResponseEntity<Conta> abrir(@RequestBody @Valid Conta conta) {
		contaService.abrirConta(conta);
		return new ResponseEntity<>(conta, HttpStatus.CREATED);
	}

	@ExceptionHandler(NegocioException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleError(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
}
