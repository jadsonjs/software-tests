/*
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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class AccountTestSteps {

    private Account account;

    private List<Account> accountList = new ArrayList<>();

    private BusinessException ex;

    @Given("all bank accounts")
    public void all_bank_accounts(io.cucumber.datatable.DataTable dataTable) {
        List<List<Object>> rows = dataTable.asLists(Object.class);

        for (List<Object> columns : rows) {
            accountList.add( new Account((String)  columns.get(0),
                    Integer.parseInt((String) columns.get(1)),
                        Double.parseDouble( (String) columns.get(2)) ) );

        }
    }

    @Given("I have a balance in my account")
    public void i_have_a_balance_in_my_account() {
        account = new Account(100d);
    }

    @When("I request a amount smaller than the balance")
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
            account.withdraw(6000d);
        }catch (BusinessException ex){
            this.ex = ex;
        }
    }
    @Then("An error message should be shown")
    public void an_error_message_should_be_shown() {
        Assertions.assertNotNull(ex);
        Assertions.assertEquals("Overdraft limit exceeded.", ex.getMessage());
    }


//    @Given("I have a balance of {double} in my account with id {int} for user {string}")
//    public void i_have_a_balance_of_in_my_account_with_id_for_user(
//            Double double1, Integer int1, String string) {
//        throw new io.cucumber.java.PendingException();
//    }


    @Given("I have {double} in my account")
    public void i_have_in_my_account(Double balance) {
        account = new Account(balance);
    }

    @When("I request {double}")
    public void i_request(Double value) {
        account.withdraw(value);
    }

    @Then("I should stay with {double}")
    public void i_should_stay_with(Double result) {
        Assertions.assertEquals(result, account.getBalance());
    }



    @Given("the account balance is ${int}")
    public void the_account_balance_is_$(Integer int1) {

    }
    @Given("My credit card is valid")
    public void my_credit_card_is_valid() {

    }
    @Given("The ATM contains enough money")
    public void the_atm_contains_enough_money() {

    }
    @Given("It is not after midnight")
    public void it_is_not_after_midnight() {

    }
    @When("I request ${int}")
    public void i_request_$(Integer int1) {

    }
    @Then("The ATM should dispense ${int}")
    public void the_atm_should_dispense_$(Integer int1) {

    }
    @Then("The account balance should be ${int}")
    public void the_account_balance_should_be_$(Integer int1) {

    }
    @Then("The card should be returned")
    public void the_card_should_be_returned() {

    }
    @Then("The ATM cannot stay empty")
    public void the_atm_cannot_stay_empty() {

    }

}
