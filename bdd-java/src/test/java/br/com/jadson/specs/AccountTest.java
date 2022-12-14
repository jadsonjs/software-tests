/*
 * Universidade Federal do Rio Grande do Norte
 * Instituto Metrópole Digital
 * Diretoria de Tecnologia da Informação
 *
 * bdd-java
 * AccountTest
 * @since 14/12/2022
 */
package br.com.jadson.specs;

import io.cucumber.junit.platform.engine.Constants;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Example of test using BDD
 *
 * Junit 4
 * @RunWith: this tells JUnit that Cucumber will take control of running the tests in this class.
 * @CucumberOptions, where we can define customizable parameters used by Cucumber when executing tests.
 *    - features = where the features are located. "classpath:specs"  "specs" directory inside the resources
 *    - tags = Execute only features file with this tag
 *    - glue = It is used to help Cucumber find classes that contain test steps.
 *    - monochrome = It is used to define the formatting of test results in the console output.
 *    - dryRun = if true, checks that each step defined in the .feature file has matching code
 *
 * Junit 5
 *    - @SelectClasspathResource where the test are localized (.feature), under the /test/resources
 *
 *    - @ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
 *
 *    - @ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@AccountTest")
 *         Execute only features file with this tag
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Suite
@SelectClasspathResource("specs")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@AccountTest")
public class AccountTest {

}
