/*
 * bdd-java
 * ReservationTest
 * @since 19/01/2023
 */
package br.com.jadson.specs;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Suite
@SelectClasspathResource("specs")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@ReservationTest")
public class ReservationTest {
}
