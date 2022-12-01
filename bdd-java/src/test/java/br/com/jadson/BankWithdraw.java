/*
 * bdd-java
 * Back
 * @since 01/12/2022
 */
package br.com.jadson;

import br.com.jadson.model.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class BankWithdraw {

    Account account;

    @Given("I have ${int} in my account.")
    public void i_have_$_in_my_account(Double int1) {
        account = new Account(int1);
    }
    @When("I request ${int}.")
    public void i_request_$(Double int1) {
        account.withdraw(int1);
    }
    @Then("The account should stay with ${int}")
    public void the_account_should_stay_with_$(Integer int1) {
        Assertions.assertEquals(80d, account.getBalance());
    }
}
