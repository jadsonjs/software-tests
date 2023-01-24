/*
 *
 * bdd-spring
 * AccountIntegrationTestSteps
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.steps;

import br.com.jadson.bddspring.model.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class AccountIntegrationTestSteps extends CucumberSpringContextConfig {

    Account account;

    ResponseEntity<Account> response;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Given("I make a initial deposit")
    public void i_make_a_initial_deposit() {
        account = new Account(100d);
    }

    @When("I request {string} to open a new account")
    public void i_request_to_open_a_new_account(String path) {
        HttpEntity<Account> httpEntity = new HttpEntity<>(account);
        response = testRestTemplate.exchange(path, HttpMethod.POST, httpEntity, Account.class);
    }

    @Then("A new account should be created")
    public void a_new_account_should_be_created() {
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(account.getBalance(), response.getBody().getBalance());

        // consult the account in database
        ParameterizedTypeReference<List<Account>> type = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<Account>> accountsResponse = testRestTemplate.exchange("/account/list", HttpMethod.GET, null, type);

        Assertions.assertEquals(1, accountsResponse.getBody().size());
        Assertions.assertEquals(account.getBalance(), accountsResponse.getBody().get(0).getBalance());
    }

    @Then("the response will return status {int}")
    public void the_response_will_return_status(Integer status) {
        Assertions.assertEquals(status, response.getStatusCode().value());
    }
}
