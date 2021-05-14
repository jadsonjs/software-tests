/**
 * 
 */
package br.com.jadson.resttest.resttest.controller;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.jadson.resttest.resttest.model.Contato;
import br.com.jadson.resttest.resttest.repository.ContatoRepository;

/**
 * AgendaControllerRestIntegrationTest.java
 *
 * <p> Alternativa conveniente do RestTemplate que é adequado para testes de integração. </p>
 * <p> Se você estiver usando a anotação @SpringBootTest, um TestRestTemplate estará automaticamente disponível e poderá ser @Autowired em seu teste. </p>
 *
 * @author  - 
 * @version 1.0
 * @since 17 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AgendaControllerRestIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ContatoRepository contatoRepository;

	private Contato contato;

	private String nome = "Chefe";

	private String ddd = "0y";

	private String telefone = "9xxxxxxx9";

	@Before
	public void start() {
		contato = new Contato(nome, ddd, telefone);
		contatoRepository.save(contato);
	}

	@After
	public void end() {
		contatoRepository.deleteAll();
	}

	/**
	 * Teste somente se status da reposta esta ok
	 */
	@Test
	public void deveMostrarTodosContatos() {
		ResponseEntity<String> resposta = testRestTemplate
				.exchange("/agenda/", HttpMethod.GET, null, String.class);
		
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
	/**
	 * Compara se o json retornado é igual a spring que queremos.
	 */
	@Test
	public void deveMostrarTodosContatosUsandoString() {
		ResponseEntity<String> resposta = 
				testRestTemplate.exchange("/agenda/",HttpMethod.GET, null, String.class);

		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
		Assert.assertTrue(resposta.getHeaders().getContentType().equals(
				MediaType.parseMediaType("application/json;charset=UTF-8")));
		
		String result = "[{\"id\":"+contato.getId()+",\"ddd\":\"0y\","
				+ "\"telefone\":\"9xxxxxxx9\",\"nome\":\"Chefe\"}]";
		
		Assert.assertEquals(result, resposta.getBody());
	}

	/**
	 *  Teste se retorna o contato inserido no banco.
	 */
	@Test
	public void deveMostrarTodosContatosUsandoList() {
		ParameterizedTypeReference<List<Contato>> tipoRetorno = new ParameterizedTypeReference<List<Contato>>() {};
		
		ResponseEntity<List<Contato>> resposta = 
				testRestTemplate.exchange("/agenda/",HttpMethod.GET, null, tipoRetorno);

		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assert.assertTrue(resposta.getHeaders().getContentType().equals(
				MediaType.parseMediaType("application/json;charset=UTF-8")));
		
		Assert.assertEquals(1, resposta.getBody().size());
		Assert.assertEquals(contato, resposta.getBody().get(0));
	}

	
	
	/**
	 * Teste um unico objeto retornado
	 */
	@Test
	public void deveMostrarUmContato() {
		ResponseEntity<Contato> resposta = 
				testRestTemplate.exchange("/agenda/contato/{id}", 
						HttpMethod.GET, null, Contato.class, contato.getId() );

		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assert.assertTrue(resposta.getHeaders().getContentType().equals(
				MediaType.parseMediaType("application/json;charset=UTF-8")));
		
		Assert.assertEquals(contato, resposta.getBody());
	}

	
	
	
	
	/**
	 * Testes busca um contato que nao existe.
	 */
	@Test
	public void buscaUmContatoDeveRetornarNaoEncontrado() {

		ResponseEntity<Contato> resposta = 
				testRestTemplate.exchange("/agenda/contato/{id}",HttpMethod.GET,null, Contato.class, 100 );

		Assert.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		Assert.assertNull(resposta.getBody());
	}
	
	
	
	
	/**
	 * Outra forma de fazer a mesma coisa
	 */
	@Test
	public void buscaUmContatoDeveRetornarNaoEncontradoComGetForEntity() {
		
		ResponseEntity<Contato> resposta = testRestTemplate.getForEntity("/agenda/contato/{id}", Contato.class, 100);

		Assert.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		Assert.assertNull(resposta.getBody());
	}

	
	
	
	/**
	 * outra forma de fazer a mesma coia
	 */
	@Test
	public void buscaUmContatoDeveRetornarNaoEncontradogetForObject() {
		
		Contato resposta = testRestTemplate.getForObject("/agenda/contato/{id}", Contato.class, 100);
		Assert.assertNull(resposta);
	}
	
	
	
	
	
	
	/////////////////////////////// Metodos POST   /////////////////////////////////
	
	
	
	
	/**
	 * Teste inserçao de um contato corretamente
	 */
	@Test
	public void inserirDeveSalvarContato() {
		
		Contato contato = new Contato(nome, ddd, telefone);
		
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		
		ResponseEntity<Contato> resposta =  testRestTemplate
				.exchange("/agenda/inserir", HttpMethod.POST, httpEntity, Contato.class);
		
		Assert.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

		Contato resultado = resposta.getBody();

		Assert.assertNotNull(resultado.getId());
		Assert.assertEquals(contato.getNome(), resultado.getNome());
		Assert.assertEquals(contato.getDdd(), resultado.getDdd());
		Assert.assertEquals(contato.getTelefone(), resultado.getTelefone());
		contatoRepository.deleteAll();
	}
	
	
	
	
	
	
	/**
	 * Tenta inserir um contato com dados invalidos 
	 */
	@Test
	public void salvarContatoDeveRetornarMensagemDeErro() {
		Contato contato = new Contato(nome, null, null);
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		
		ResponseEntity<String> resposta = testRestTemplate.exchange("/agenda/inserir", HttpMethod.POST, httpEntity
				, new ParameterizedTypeReference<String>() {});
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
		Assert.assertTrue(resposta.getBody().contains("O DDD deve ser preenchido"));
		Assert.assertTrue(resposta.getBody().contains("O Telefone deve ser preenchido"));
	}

	
	
	
	

	/**
	 * Uso do método PostforEntity 
	 */
	@Test
	public void inserirDeveSalvarContatoComPostForEntity() {
		
		Contato contato = new Contato(nome, ddd, telefone);
		
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		ResponseEntity<Contato> resposta =  testRestTemplate.postForEntity("/agenda/inserir",httpEntity, Contato.class);
		Assert.assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
		
		Contato resultado = resposta.getBody();
		
		
		Assert.assertNotNull(resultado.getId());
		Assert.assertEquals(contato.getNome(), resultado.getNome());
		Assert.assertEquals(contato.getDdd(), resultado.getDdd());
		Assert.assertEquals(contato.getTelefone(), resultado.getTelefone());
		contatoRepository.deleteAll();
	}

	
	
	/**
	 * Teste do metodo post for object
	 */
	@Test
	public void inserirContatoDeveSalvarContatoPostForObject() {
		Contato contato = new Contato(nome, ddd, telefone);
		
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		Contato resposta = testRestTemplate.postForObject("/agenda/inserir",httpEntity, Contato.class);

		Assert.assertNotNull(resposta.getId());
		Assert.assertEquals(contato.getNome(), resposta.getNome());
		Assert.assertEquals(contato.getDdd(), resposta.getDdd());
		Assert.assertEquals(contato.getTelefone(), resposta.getTelefone());
		contatoRepository.deleteAll();
	}
	
	
	
	/////////////////////////////// Testes do metodo PUT //////////////////////////////
	
	
	
	@Test
	public void alterarDeveRetornarMensagemDeErro() {
		contato.setDdd(null);
		contato.setTelefone(null);
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		ResponseEntity<String> resposta = 
				testRestTemplate.exchange("/agenda/alterar/{id}", HttpMethod.PUT
						, httpEntity, new ParameterizedTypeReference<String>() {},contato.getId());
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
		Assert.assertTrue(resposta.getBody().contains("O DDD deve ser preenchido"));
		Assert.assertTrue(resposta.getBody().contains("O Telefone deve ser preenchido"));
	}

	
	
	@Test
	public void alterarDeveAlterarContato() {
		contato.setNome("Novo Chefe");
		
		HttpEntity<Contato> httpEntity = new HttpEntity<>(contato);
		ResponseEntity<Contato> resposta = 
				testRestTemplate.exchange("/agenda/alterar/{id}", HttpMethod.PUT, 
						httpEntity, Contato.class, contato.getId());
		
		Assert.assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
		Contato resultado = resposta.getBody(); 
		Assert.assertEquals(contato.getId(), resultado.getId());
		Assert.assertEquals(ddd, resultado.getDdd());
		Assert.assertEquals(telefone, resultado.getTelefone());
		Assert.assertEquals("Novo Chefe", resultado.getNome());
	}

	
	
	
	
	@Test
	public void alterarDeveAlterarContatoComPut() {
		contato.setNome("Novo Chefe");
		testRestTemplate.put("/agenda/alterar/{id}",contato,contato.getId());

		Contato resultado = contatoRepository.findById(contato.getId()).get();
		Assert.assertEquals(ddd, resultado.getDdd());
		Assert.assertEquals(telefone, resultado.getTelefone());
		Assert.assertEquals("Novo Chefe", resultado.getNome());
	}

	
	
	@Test
	public void removerDeveExcluirContato() {
		ResponseEntity<Contato> resposta = 
				testRestTemplate.exchange("/agenda/remover/{id}",HttpMethod.DELETE, null
						, Contato.class,contato.getId());
		
		Assert.assertEquals(HttpStatus.NO_CONTENT,resposta.getStatusCode());
		Assert.assertNull(resposta.getBody());
	}
	
	

	@Test
	public void removerDeveExcluirContatoComDelete() {
		testRestTemplate.delete("/agenda/remover/"+contato.getId());
		
		Optional<Contato> resultado = contatoRepository.findById(contato.getId());
		Assert.assertEquals(Optional.empty(), resultado);
	}
	
	
}
