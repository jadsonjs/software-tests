/*
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
 */
package br.com.jadson.integrationtest.controller;

import br.com.jadson.integrationtest.model.Conta;
import br.com.jadson.integrationtest.model.Correntista;
import br.com.jadson.integrationtest.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

/**
 * ContaControllerIntegrationTest.java
 *
 * Test the @MockBean annotation
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContraControllerIntegrationTest {
	/**
	 * @MockBean Cria Mock para objetos do Spring
	 * Spring usa usa esse mock quando for injetar o contaRepository no contaService
	 */
	@MockBean
	private ContaRepository contaRepository;
	@Autowired
	private ContaController contaController;

	/**
	 * Default Test
	 */
	@Test
	void abrirConta() {

		contaController.abrirConta(100.0d, "000212-3", 123458388354l, "Teste");

		Mockito.when(contaRepository.findAll()).thenReturn(
				Arrays.asList(new Conta[]{new Conta(100.0d, "000212-3", new Correntista(123458388354l, "Teste"))}));

		List<Conta> contas = contaRepository.findAll();
		Assertions.assertEquals(1, contas.size());
		Assertions.assertEquals("000212-3", contas.get(0).getNumero());
		Assertions.assertEquals(100.0d, contas.get(0).getSaldo());
		Assertions.assertEquals(123458388354l, contas.get(0).getCorrentista().getCpf());

	}
}


