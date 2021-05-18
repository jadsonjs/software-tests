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
 * integration-test
 * br.com.jadson.integrationtest
 * JPAIntegrationTest
 * 18/05/21
 */
package br.com.jadson.integrationtest.repository;

import br.com.jadson.integrationtest.model.Conta;
import br.com.jadson.integrationtest.model.Correntista;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Integration test with JPA
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JPAIntegrationTest {

    @Autowired
    ContaRepository contaRepository;

    Conta conta;

    @BeforeEach
    void init(){
        conta = new Conta(250.0d, "12323-3",
                new Correntista(12345678900l, "Teste"));
    }

    @Test
    void testValidation(){
        conta.setNumero(null);
        ConstraintViolationException ex =
                Assertions.assertThrows(ConstraintViolationException.class,
                    () -> contaRepository.save(conta) );

        Assertions.assertEquals("O número da conta não pode ser vazio",
                ex.getConstraintViolations().iterator().next().getMessage());
    }

    @Test
    void testContaSendoSalva(){

        contaRepository.save(conta);
        Conta contaSalva = contaRepository.findFirstByNumero(conta.getNumero());

        Assertions.assertNotNull(contaSalva);
        Assertions.assertTrue(contaSalva.getId() > 0);
        Assertions.assertEquals(conta.getNumero(), contaSalva.getNumero());
        Assertions.assertEquals(conta.getCorrentista().getCpf(), contaSalva.getCorrentista().getCpf());

    }

    @Test
    void testQuantidadesContasSalvas(){
        Conta conta2 = new Conta(3500.0d, "00001-3",
                new Correntista(12345678900l, "Classe C"));
        contaRepository.save(conta2);
        List<Conta> contas = contaRepository.findAll();
        Assertions.assertTrue(contas.size() == 1);
    }

}




