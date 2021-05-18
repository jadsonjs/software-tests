/**
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.jadson.resttest.resttest.controller;

import br.com.jadson.resttest.resttest.model.Conta;
import br.com.jadson.resttest.resttest.model.Correntista;
import br.com.jadson.resttest.resttest.repository.ContaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ContaControllerRestIntegrationTest1.java
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContaControllerRestIntegrationTest1 {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ContaRepository contaRepository;


	private Conta conta;

	@BeforeEach
	public void setup() {
		conta = new Conta(250.0d, "12323-3", new Correntista(12345678900l, "Teste"));
		contaRepository.save(conta);
	}

	@AfterEach
	public void teardown() {
		contaRepository.deleteAll();
	}

	/**
	 * Testa se ao chamar o metodo /conta/  retorna a lista de contas salvas no banco
	 */
	@Test
	public void listContasTest() {
		ParameterizedTypeReference<List<Conta>> tipoRetorno = new ParameterizedTypeReference<List<Conta>>() {};

		ResponseEntity<List<Conta>> resposta =
				testRestTemplate.exchange("/conta/", HttpMethod.GET, null, tipoRetorno);

		Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assertions.assertTrue(resposta.getHeaders().getContentType().equals(MediaType.parseMediaType("application/json")));

		Assertions.assertEquals(1, resposta.getBody().size());
		Assertions.assertEquals(conta, resposta.getBody().get(0));
	}


	/**
	 * Teste um unico objeto retornado
	 */
	@Test
	public void getContaTest() {
		ResponseEntity<Conta> resposta =
				testRestTemplate.exchange("/conta/conta/{id}",
						HttpMethod.GET, null, Conta.class, conta.getId() );

		Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Assertions.assertTrue(resposta.getHeaders().getContentType().equals(
				MediaType.parseMediaType("application/json")));

		Assertions.assertEquals(conta, resposta.getBody());
	}

	@Test
	public void buscaContaNaoExiste() {
		Long ID_CONTA_NAO_EXISTE = 100l;

		ResponseEntity<Conta> resposta =
				testRestTemplate.exchange("/conta/conta/{id}",
						HttpMethod.GET,null, Conta.class, ID_CONTA_NAO_EXISTE );

		Assertions.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
		Assertions.assertNull(resposta.getBody());
	}


	@Test
	public void testAbrirConta() {

		Conta novaConta = new Conta(1000.0d, "11000-8", new Correntista(94345678900l, "Maria"));

		HttpEntity<Conta> httpEntity = new HttpEntity<>(novaConta);

		ResponseEntity<Conta> resposta =
				testRestTemplate.exchange("/conta/abrir", HttpMethod.POST, httpEntity, Conta.class);

		Assertions.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

		Conta resultado = resposta.getBody();

		Assertions.assertNotNull(resultado.getId());
		Assertions.assertEquals(novaConta.getNumero(), resultado.getNumero());
		Assertions.assertEquals(novaConta.getSaldo(), resultado.getSaldo());
		Assertions.assertEquals(novaConta.getCorrentista().getCpf(), resultado.getCorrentista().getCpf());
	}

	@Test
	public void testAbrirContaNegativado() {

		Conta contaNoVermelho = new Conta(-1000.0d, "12000-3", new Correntista(94345678900l, "Maria"));
		contaRepository.save(contaNoVermelho);

		Conta novaConta = new Conta(1000.0d, "11000-8", new Correntista(94345678900l, "Maria"));

		HttpEntity<Conta> httpEntity = new HttpEntity<>(novaConta);

		ResponseEntity<String> resposta =
				testRestTemplate.exchange("/conta/abrir", HttpMethod.POST, httpEntity, String.class);

		Assertions.assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
		Assertions.assertTrue(resposta.getBody().contains("Não é possível abrir conta. Correntista possui débitos"));

	}


	@Test
	public void testAbrirConta2() {

		Conta novaConta = new Conta(1000.0d, "11000-8", new Correntista(94345678900l, "Maria"));

		HttpEntity<Conta> httpEntity = new HttpEntity<>(novaConta);

		ResponseEntity<Conta> resposta =
				testRestTemplate.postForEntity("/conta/abrir", httpEntity, Conta.class);

		Assertions.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

		Conta resultado = resposta.getBody();

		Assertions.assertNotNull(resultado.getId());
		Assertions.assertEquals(novaConta.getNumero(), resultado.getNumero());
		Assertions.assertEquals(novaConta.getSaldo(), resultado.getSaldo());
		Assertions.assertEquals(novaConta.getCorrentista().getCpf(), resultado.getCorrentista().getCpf());
	}
	
	
}
