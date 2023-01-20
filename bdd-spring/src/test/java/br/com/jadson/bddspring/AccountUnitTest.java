/*
 *
 * bdd-spring
 * AccountUnitTest
 * @since 20/01/2023
 */
package br.com.jadson.bddspring;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Suite
@SelectClasspathResource("requirements")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@UnitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountUnitTest {

}
