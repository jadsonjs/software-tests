/*
 * bdd-spring
 * BankRepository
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.repositories;

import br.com.jadson.bddspring.model.Account;
import org.springframework.data.repository.CrudRepository;

/**
 *
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

}
