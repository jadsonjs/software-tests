/*
 * bdd-spring
 * BankController
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.controllers;

import br.com.jadson.bddspring.model.Account;
import br.com.jadson.bddspring.services.BankService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@RestController
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/account/list")
    public List<Account> listAccounts() {
        return bankService.listAccounts();
    }

    @PostMapping(path="/account/open", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account openAccount(@RequestBody Account account) {
        return bankService.openAccount(account);
    }

}
