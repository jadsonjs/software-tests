/*
 * bdd-spring
 * CucumberSpringContextConfig
 * @since 20/01/2023
 */
package com.example.bddspringselenium;

import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringContextConfig {

    @Autowired
    TestRestTemplate testRestTemplate;

    WebDriver driver;
}
