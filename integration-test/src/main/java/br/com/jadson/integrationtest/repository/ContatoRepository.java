/**
 * 
 */
package br.com.jadson.integrationtest.repository;

import br.com.jadson.integrationtest.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ContatoRepository.java
 *
 * <p></p>
 *
 * @author  - 
 * @version 1.0
 * @since 12 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
