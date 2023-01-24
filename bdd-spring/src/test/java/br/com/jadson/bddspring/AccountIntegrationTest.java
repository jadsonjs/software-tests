/*
 *
 * bdd-spring
 * AccountIntegrationTest
 * @since 20/01/2023
 */
package br.com.jadson.bddspring;

import io.cucumber.core.options.Constants;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Suite

// where the features files are located under "src/test/resources"
@SelectClasspathResource("requirements")
// package name where is "glue" code will be. The java source code "glue" with the features in .features file
@ConfigurationParameters({
    //@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,     value = "br.com.jadson.bddspring.config"  ),
    //@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,     value = "br.com.jadson.bddspring.steps"  ),

    // show the message to publish report
    @ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "false"),

    // filter test with this tag
    @ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@IntegrationTest")
})
public class AccountIntegrationTest {

}
