/**
 * 
 */
package br.com.jadson.integrationtest.service;

import br.com.jadson.integrationtest.exception.ContatoException;
import br.com.jadson.integrationtest.model.Contato;

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

	void inserir(Contato contato) throws ContatoException;

	void remover(Long id);

}
