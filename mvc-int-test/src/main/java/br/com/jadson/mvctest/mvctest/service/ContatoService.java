/**
 * 
 */
package br.com.jadson.mvctest.mvctest.service;

import java.util.List;
import java.util.Optional;

import br.com.jadson.mvctest.mvctest.model.Contato;

/**
 * ContatoService.java
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
public interface ContatoService {
	
	void inserir(Contato contato);

	void remover(Long id);

	List<Contato> buscarContatos();

	Contato buscarContato(Long id);
	
	Optional<Contato> buscarContatoOpcional(Long id);

	Contato inserirOuAlterar(Contato contato);


}
