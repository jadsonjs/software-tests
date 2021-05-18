/*
 *
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
 *
 *
 * mvc-int-test
 * br.com.jadson.mvctest.mvctest.controller
 * ContraControllerIntegrationTest4
 * 18/05/21
 */
package br.com.jadson.mvctest.mvctest.controller;

import br.com.jadson.mvctest.mvctest.exception.NegocioException;
import br.com.jadson.mvctest.mvctest.model.Conta;
import br.com.jadson.mvctest.mvctest.model.Correntista;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager //  provê um TestEntityManager que ajuda a configurar dados de teste no banco de dados
@Transactional
public class ContraControllerIntegrationTest4 {

    @Autowired
    private MockMvc mockMvc;

    /***
     * Entity manager para testes, permite inserir dados no banco de dados de teste
     */
    @Autowired
    private TestEntityManager testEntityManager;


    private Conta conta;

    @BeforeEach
    public void setup() {
        conta = new Conta(250.0d, "12323-3",
                new Correntista(12345678900l, "Teste"));
        testEntityManager.persist(conta);
    }

    @AfterEach
    public void teardown() {
        testEntityManager.getEntityManager().createQuery("DELETE FROM Conta").executeUpdate();
    }

    /**
     * Inseri uma conta no banco e realizar um get passando o id dessa conta
     * e verifica o objeto retornado
     * @throws Exception
     */
    @Test
	public void testFindConta() throws Exception {
		Long id = (Long) testEntityManager.persistAndGetId(conta);
		mockMvc.perform( MockMvcRequestBuilders.get("/conta/conta/{id}", id) )
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("conta/conta"))
		.andExpect(MockMvcResultMatchers.model().attribute("conta", Matchers.any(Conta.class)))
		.andExpect(MockMvcResultMatchers.model().attribute("conta", conta))
		.andDo(MockMvcResultHandlers.print());
	}


	/**
	 * Teste a insericao de um contato valido
	 *
	 * Realizar um post para /conta/abrir passando os parametos e  verifica
	 *
	 * se retornou se retornou o status OK
	 * Se retornou para view correta
	 * Se retornou o objeto conta correta
	 * Se o objeto conta foi salvo no banco
	 *
	 * @throws Exception
	 */
	@Test
	public void testAbrirContaController() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/conta/abrir")
				.param("saldo", "150.0d")
				.param("numero", "000023-3")
				.param("correntista.cpf", "12345678900")
                .param("correntista.nome", "José")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("/conta/abrir"))
		.andExpect(MockMvcResultMatchers.model().attribute("conta", Matchers.any(Conta.class)))
		.andExpect(MockMvcResultMatchers.model().attribute("successMessage", "Conta aberta com sucesso."))
		.andDo(MockMvcResultHandlers.print());

		Query query = testEntityManager.getEntityManager().createQuery("SELECT c FROM Conta c");

		@SuppressWarnings("unchecked")
        List<Conta> contaList = query.getResultList();

		Assertions.assertEquals(2, contaList.size()); // o é inserido no comeco do teste e esse agora
	}


	@Test
	public void testAbrirContaControllerNegativado() throws Exception {

		Conta contaNoVermelho = new Conta(-1000.0d, "12000-3", new Correntista(12345678900l, "José"));
		testEntityManager.persist(contaNoVermelho);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/conta/abrir")
				.param("saldo", "150.0d")
				.param("numero", "000023-3")
				.param("correntista.cpf", "12345678900")
				.param("correntista.nome", "José");

		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof NegocioException))
				.andExpect(result -> Assertions.assertEquals("Não é possível abrir conta. Correntista possui débitos",
						result.getResolvedException().getMessage()));

	}

}
