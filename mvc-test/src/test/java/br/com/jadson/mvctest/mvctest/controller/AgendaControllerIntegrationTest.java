/**
 * 
 */
package br.com.jadson.mvctest.mvctest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.persistence.Query;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import br.com.jadson.mvctest.mvctest.model.Contato;

/**
 * AgendaControllerIntegrationTest.java
 *
 * <p>Testa de integracao do SPRING MVC</p>
 * 
 * @SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT) = inicia um servidor web em uma porta aleatoria para roda os testes
 *
 * @AutoConfigureMockMvc = Realiza a configuracao necessaria para usar o MockMvc
 * 
 * @AutoConfigureTestEntityManager = provê um TestEntityManager que ajuda a configurar dados de teste no banco de dados
 * 
 * @Transactional = Obrigatório quando utilizar TestEntityManager
 *
 * @author  - 
 * @version 1.0
 * @since 16 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class AgendaControllerIntegrationTest {
	
	/**
	 * Simula a execucao de uma requisições WEB
	 */
	@Autowired
	private MockMvc mockMvc;
	
	/***
	 * Entity manager para testes, permite inserir dados no banco de dados de teste
	 */
	@Autowired
	private TestEntityManager testEntityManager;

	private Contato contato;
	
	@Before
	public void start() {
		contato = new Contato("Eu", "84", "555-5555");
		testEntityManager.persist(contato);
	}
	
	@After
	public void end() {
		testEntityManager.getEntityManager().createQuery("DELETE FROM Contato").executeUpdate();
	}
	
	/**
	 * Realiza um get e verifica se o status retornado foi OK
	 * @throws Exception
	 */
	@Test
	public void testSimplesGet() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		result.andExpect( MockMvcResultMatchers.status().isOk() );
	}
	
	
	/**
	 * Checa o status da requisicao
	 * 
	 * @throws Exception
	 */
	@Test
	public void checaStatus() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		StatusResultMatchers status = MockMvcResultMatchers.status();

		resultActions.andExpect(status.isOk());

		resultActions.andExpect(status.is(200));

		resultActions.andExpect(status.is(Matchers.is(200)));

	}

	/**
	 * Teste se o view é o view esperado
	 * 
	 * @throws Exception
	 */
	@Test
	public void checaView() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		ViewResultMatchers view = MockMvcResultMatchers.view();

		resultActions.andExpect(view.name("agenda"));  // se o view retornaou para a pagina "agenda.html"

		resultActions.andExpect(view.name(Matchers.is("agenda")));
	}

	
	
	/**
	 * Teste o retorno do request, se possui os dados desejados
	 * 
	 * @throws Exception
	 */
	@Test
	public void checaModel() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		ModelResultMatchers model = MockMvcResultMatchers.model();

		resultActions.andExpect(model.attributeExists("contatos"));

		resultActions.andExpect(model.attribute("contatos", Matchers.hasSize(1)));

		resultActions.andExpect(model.attribute("contatos",
				Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("id", Matchers.is(contato.getId())),
						Matchers.hasProperty("nome", Matchers.is(contato.getNome())),
						Matchers.hasProperty("ddd", Matchers.is(contato.getDdd())),
						Matchers.hasProperty("telefone", Matchers.is(contato.getTelefone()))))));
	}
	
	
	
	/**
	 * Recuperando a status 
	 * @throws Exception
	 */
	@Test
	public void checaStatus2() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());

		Integer status = resultActions.andReturn().getResponse().getStatus();

		Assert.assertTrue(status.equals(200));

	}

	/**
	 * Recuperando a view
	 * @throws Exception
	 */
	@Test
	public void checaView2() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());

		ModelAndView mav = resultActions.andReturn().getModelAndView();

		Assert.assertEquals("agenda", mav.getViewName());

	}

	/**
	 * Recuperando os dados do model
	 * @throws Exception
	 */
	@Test
	public void checaModel2() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/"));
		resultActions.andDo(MockMvcResultHandlers.print());

		ModelAndView mav = resultActions.andReturn().getModelAndView();

		@SuppressWarnings("unchecked")
		List<Contato> contatos = (List<Contato>) mav.getModel().get("contatos");

		Assert.assertEquals(1, contatos.size());

		Assert.assertTrue(contatos.contains(contato));

	}
	
	
	@Test
	public void deveMostrarTodosOsContatos() throws Exception {
		testEntityManager.persist(contato);
		mockMvc.perform(get("/agenda/"))
		.andExpect(status().isOk())
		.andExpect(view().name("agenda"))
		.andExpect(model().attribute("contatos", Matchers.hasSize(1)))
		.andExpect(model().attribute("contatos", Matchers.hasItem(
				Matchers.allOf(
						Matchers.hasProperty("id", Matchers.is(contato.getId())),
						Matchers.hasProperty("nome", Matchers.is(contato.getNome())),
						Matchers.hasProperty("ddd", Matchers.is(contato.getDdd())),
						Matchers.hasProperty("telefone", Matchers.is(contato.getTelefone()))
						)
				)))
		.andDo(print());
	}
	
	
	

	/**
	 * Persiste um contato no banco de testes
	 * Realizar um get para retornar o contato
	 * 
	 * Verifica se retorna status OK
	 * Se retornar para a view /agenda/conatato
	 * Se o contato retornado é igual ao inserido
	 * 
	 * @throws Exception
	 */
	@Test
	public void deveMostrarUmContato() throws Exception {
		Long id = (Long) testEntityManager.persistAndGetId(contato);
		mockMvc.perform( get("/agenda/contato/{id}", id) )
		.andExpect(status().isOk())
		.andExpect(view().name("agenda/contato"))
		.andExpect(model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(model().attribute("contato",contato))
		.andDo(print());
	}

	
	
	
	/**
	 * Persiste 1 contato retornando o id
	 * 
	 * Realiza um "delete" para remover
	 * 
	 * - Verifica se o status retornado é algum dos status 300, 
	 * - se foi realizado um redirect para "agenda/", 
	 * - Se contem um atributo flash com a mensagem de remocao com sucesso
	 * - imprime a reposta
	 * - consultas no banco e verifica se contem resultado
	 * 
	 * @throws Exception
	 */
	@Test
	public void removerDeveExcluirContato() throws Exception {
		Long id = (Long) testEntityManager.persistAndGetId(contato);
		mockMvc.perform( delete("/agenda/remover/{id}", id) )
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:agenda/"))
		.andExpect(flash().attribute("successMessage", "Contato removido com sucesso"))
		.andDo(print());

		Query query = testEntityManager.getEntityManager().createQuery("SELECT c FROM Contato c");
		
		@SuppressWarnings("unchecked")
		List<Contato> resultList = query.getResultList();

		Assert.assertEquals(0, resultList.size());
	}
	
	
	
	
	/**
	 * Testa erro ao inserir um contato invalido
	 * 
	 * @throws Exception
	 */
	@Test
	public void inserirComDddNuloDeveRetornarUmErro() throws Exception {
		mockMvc.perform(post("/agenda/inserir")
				.param("telefone", "555-5555")
				.param("nome", "Eu ")
				)
		.andExpect(status().isOk())
		.andExpect(view().name("agenda/inserir"))
		.andExpect(model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(model().attributeHasFieldErrors("contato", "ddd"))
		.andExpect(model().attributeHasFieldErrorCode("contato", "ddd", "NotEmpty")) // teste se esse campo retorna mensagem de erro!
		.andDo(print());
	}
	
	
	
	/**
	 * Teste a insericao de um contato valido 
	 * 
	 * Realizar um post para /agenda/inserir passando os parametos e  verifica 
	 * 
	 * se retornou se retornou o status OK
	 * Se retornou para view correta
	 * Se retornou o objeto contato correto 
	 * Se o objeto contato foi salvo no banco
	 * 
	 * @throws Exception
	 */
	@Test
	public void inserirDeveSalvaContato() throws Exception {
		mockMvc.perform(post("/agenda/inserir")
				.param("ddd", "84")
				.param("telefone", "55-5555")
				.param("nome", "Eu")
				)
		.andExpect(status().isOk())
		.andExpect(view().name("agenda/inserir"))
		.andExpect(model().attribute("contato", Matchers.any(Contato.class)))
		.andExpect(model().attribute("successMessage", "Contato cadastrado com sucesso"))
		.andDo(print());

		Query query = testEntityManager.getEntityManager().createQuery("SELECT c FROM Contato c");
		
		@SuppressWarnings("unchecked")
		List<Contato> resultList = query.getResultList();
		
		Assert.assertEquals(2, resultList.size()); // o é inserido no comeco do teste e esse agora
	}

}
