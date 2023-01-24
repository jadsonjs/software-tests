/*
 * bdd-spring
 * CucumberSpringContextConfig
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Configuration of spring with cucumber
 *
 * In order to integrate Spring Dependence Injection with Cucumber glue code or Step Definitions,
 * we would need to do this
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */

// This will ensure that all Spring Beans are available for use in our Cucumber Step Definitions.
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //load the spring boot context
public class CucumberSpringContextConfig {
}
