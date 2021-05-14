package br.com.jadson.integrationtest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.jadson.integrationtest.controller.AgendaControllerIntegrationTest;
import br.com.jadson.integrationtest.repository.ContatosRepositoryIntegrationTest;
import br.com.jadson.integrationtest.service.ContatosServiceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ AgendaControllerIntegrationTest.class, ContatosServiceIntegrationTest.class, ContatosRepositoryIntegrationTest.class })
public class AllIntegrationsTests {

}
