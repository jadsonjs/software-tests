/*
 *
 * bdd-spring
 * AccountIntegrationTestSteps
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.steps;

import br.com.jadson.bddspring.model.Account;
import br.com.jadson.bddspring.repositories.AccountRepository;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * It stores the mapping between each step of the scenario
 * defined in the feature file with a code of function to be executed.
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Slf4j
public class AccountIntegrationTestSteps extends CucumberSpringContextConfig {

    Account account;

    ResponseEntity<Account> response;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AccountRepository accountRepository;


    //this method executes before every scenario
    @Before
    public void before() {
        log.info("Before scenario ");
    }

    //this method executes before every step
    @BeforeStep
    public void beforeStep() {
        log.info("BeforeStep ");
    }

    //this method executes after every step
    @AfterStep
    public void afterStep() {
        log.info("AfterStep");
    }

    //this method executes after every scenario
    @After
    public void after() {
        log.info("after scenario");
        accountRepository.deleteAll();
    }



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
