/*
 *
 * bdd-spring-selenium
 * BddTests
 * @since 05/05/2023
 */
package com.example.bddspringselenium;

import io.cucumber.core.options.Constants;
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.*;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Suite
@SelectClasspathResource("requirements")
@ConfigurationParameters({
        @ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "false")
})
public class BddCucumberTests {
}
