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
 * ContraControllerIntegrationTest3
 * 18/05/21
 */
package br.com.jadson.mvctest.mvctest.controller;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager // @AutoConfigureTestEntityManager = provê um TestEntityManager que ajuda a configurar dados de teste no banco de dados
@Transactional
public class ContraControllerIntegrationTest3 {

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

    @Test
    public void verificaContasRetornadas() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/conta/"));
        ModelResultMatchers model = MockMvcResultMatchers.model();

        resultActions.andExpect(model.attributeExists("contas"));

        resultActions.andExpect(model.attribute("contas", Matchers.hasSize(1)));

        resultActions.andExpect(
                model.attribute("contas",
                    Matchers.hasItem(
                            Matchers.allOf(
                                Matchers.hasProperty("numero", Matchers.is(conta.getNumero())),
                                Matchers.hasProperty("saldo", Matchers.is(conta.getSaldo()))
                            )
                    )
                )
        );
	}


    @Test
	public void obtendoObjetosRetornados() throws Exception {
	    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/conta/"));
		resultActions.andDo(MockMvcResultHandlers.print());

		ModelAndView mav = resultActions.andReturn().getModelAndView();

        Assertions.assertEquals("conta", mav.getViewName());

        @SuppressWarnings("unchecked")
        List<Conta> contas = (List<Conta>) mav.getModel().get("contas");

        Assertions.assertEquals(1, contas.size());
		Assertions.assertTrue(contas.contains(conta));
	}


}
