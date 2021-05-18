/**
 * 
 */
package br.com.jadson.resttest.resttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jadson.resttest.resttest.model.Contato;

/**
 * ContatoRepository.java
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
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
