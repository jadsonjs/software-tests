/*
 * bdd-spring
 * BankService
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.services;

import br.com.jadson.bddspring.model.Account;
import br.com.jadson.bddspring.repositories.AccountRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Service
public class BankService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> listAccounts() {
        return ImmutableList.copyOf(accountRepository.findAll());
    }

    public Account openAccount(Account account) {
        if(account.getBalance() <= 0.0){
            throw new RuntimeException("Can not open a account with a negative balance");
        }
        return accountRepository.save(account);
    }
}
