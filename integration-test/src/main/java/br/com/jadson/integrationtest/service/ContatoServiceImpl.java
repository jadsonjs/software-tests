/**
 * 
 */
package br.com.jadson.integrationtest.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jadson.integrationtest.exception.ContatoException;
import br.com.jadson.integrationtest.model.Contato;
import br.com.jadson.integrationtest.repository.ContatoRepository;

/**
 * ContatoServiceImpl.java
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
@Service
public class ContatoServiceImpl implements ContatoService{

	@Autowired
	private ContatoRepository contatoRepository;

	@Override
	public void inserir(Contato contato) throws ContatoException {
		try {
			contatoRepository.save(contato);
		} catch (ConstraintViolationException e) {
			throw new ContatoException(e);
		}
	}

	@Override
	public void remover(Long id) {
		contatoRepository.deleteById(id);
	}

}

