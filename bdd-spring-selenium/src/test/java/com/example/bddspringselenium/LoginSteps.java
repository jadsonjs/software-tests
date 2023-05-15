/*
 * bdd-spring-selenium
 * LoginSteps
 * @since 05/05/2023
 */
package com.example.bddspringselenium;

import com.example.bddspringselenium.model.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class LoginSteps extends CucumberSpringContextConfig{

    User user;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/drives/linux_64/chromedriver").toString());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @Given("I have a user")
    public void i_have_a_user() {
        user = new User();
    }
    @And("with a valid email")
    public void with_a_valid_email() {
        user.setEmail("teste@teste.imd.ufrn.br");
    }
    @And("with a valid password")
    public void with_a_valid_password() {
        user.setPassword("123456");
    }
    @When("I submit the from")
    public void i_submit_the_from() {
        driver.navigate().to("http://localhost:8080");
        driver.findElement(By.id("email")).sendKeys(user.getEmail());
        driver.findElement(By.id("password")).sendKeys(user.getPassword());
        driver.findElement(By.name("loginButton")).click();
    }
    @Then("I should be redirect to home page")
    public void i_should_be_redirect_to_home_page() {
        Assertions.assertEquals("Home Page", driver.findElement(By.tagName("h1")).getText());
        Assertions.assertTrue(driver.findElement(By.tagName("p")).getText().contains(user.getEmail()));
    }

    @After
    public void after() {
        driver.close();
        driver.quit();
    }
}
