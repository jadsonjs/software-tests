/*
 * bdd-spring
 * AccountUnitTestSteps
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.steps;

import br.com.jadson.bddspring.model.Account;
import br.com.jadson.bddspring.services.BankService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class AccountUnitTestSteps {

    Account account;

    @Autowired
    BankService bankService;

    @Given("I make a initial deposit")
    public void i_make_a_initial_deposit() {
        account = new Account(100d);
    }
    @When("I request to open a new account")
    public void i_request_to_open_a_new_account() {
        account = bankService.openAccount(account);
    }
    @Then("A new account should be created")
    public void a_new_account_should_be_created() {
        Assertions.assertNotNull(account);
        Assertions.assertEquals(100d, account.getBalance());
    }

}
