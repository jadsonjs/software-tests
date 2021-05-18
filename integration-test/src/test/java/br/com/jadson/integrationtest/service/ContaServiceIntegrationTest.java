/*
 *
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
 */
package br.com.jadson.integrationtest.service;

import br.com.jadson.integrationtest.exception.NegocioException;
import br.com.jadson.integrationtest.model.Conta;
import br.com.jadson.integrationtest.model.Correntista;
import br.com.jadson.integrationtest.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContaServiceIntegrationTest {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    private Conta conta;

	@BeforeEach
	public void init() {
        conta = new Conta(250.0d, "12323-3",
                new Correntista(12345678900l, "Teste"));
	}

    /**
     * Default Test
     */
    @Test
    void abrirConta() {

        contaService.abrirConta(conta);

		List<Conta> contas = contaRepository.findAll();
		Assertions.assertEquals(1, contas.size());
        contaRepository.deleteAll();
    }

    /**
     * Should block account opening
     */
    @Test
    void abrirContaCorrentistaPossuiDebito() {

        contaRepository.save(new Conta(-100.0d, "12323-3",
                new Correntista(12345678900l, "Teste")));

        NegocioException ex =
                Assertions.assertThrows(NegocioException.class,
                        () -> contaService.abrirConta(conta));

        Assertions.assertEquals("Não é possível abrir conta. Correntista possui débitos", ex.getMessage());

        contaRepository.deleteAll();
    }
}