/**
 * 
 */
package br.com.jadson.mvctest.mvctest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jadson.mvctest.mvctest.model.Contato;
import br.com.jadson.mvctest.mvctest.repository.ContatoRepository;

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
	public void inserir(Contato contato) {
		contatoRepository.save(contato);
	}
	
	@Override
	public Contato inserirOuAlterar(Contato contato) {
		return contatoRepository.save(contato);
	}

	@Override
	public void remover(Long id) {
		contatoRepository.deleteById(id);
	}

	@Override
	public List<Contato> buscarContatos() {
		return contatoRepository.findAll();
	}

	@Override
	public Contato buscarContato(Long id) {
		return contatoRepository.getOne(id);
	}
	
	@Override
	public Optional<Contato> buscarContatoOpcional(Long id) {
		return contatoRepository.findById(id);
	}

}
