/*
 * Universidade Federal do Rio Grande do Norte
 * Instituto Metrópole Digital
 * Diretoria de Tecnologia da Informação
 *
 * bdd-java
 * AccountTestSteps
 * @since 14/12/2022
 */
package br.com.jadson.specs.steps;

import br.com.jadson.exceptions.BusinessException;
import br.com.jadson.model.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class AccountTestSteps {

    private Account account;

    private BusinessException ex;

    @Given("I have a balance in my account.")
    public void i_have_a_balance_in_my_account() {
        account = new Account(100d);
    }

    @When("I request a amount smaller than the balance.")
    public void i_request_a_amount_smaller_than_the_balance() {
        account.withdraw(20d);
    }

    @Then("The withdraw should be done")
    public void the_withdraw_should_be_done() {
        Assertions.assertEquals(80d, account.getBalance());
    }

    @When("I request an amount greater than the balance")
    public void i_request_an_amount_greater_than_the_balance() {
        try {
            account.withdraw(200d);
        }catch (BusinessException ex){
            this.ex = ex;
        }
    }
    @Then("An error message should be shown.")
    public void an_error_message_should_be_shown() {
        Assertions.assertEquals("Overdraft limit exceeded.", ex.getMessage());
    }


}
