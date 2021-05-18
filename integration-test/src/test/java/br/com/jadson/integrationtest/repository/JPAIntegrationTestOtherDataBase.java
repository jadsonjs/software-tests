/*
 * Federal University of Rio Grande do Norte
 * Department of Informatics and Applied Mathematics
 * Collaborative & Automated Software Engineering (CASE) Research Group
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
 * JPAIntegrationTest2
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
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Execute the tests with other database. it need to be in classpath
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
public class JPAIntegrationTestOtherDataBase {

    @Autowired
    ContaRepository contaRepository;

    Conta conta;

    @BeforeEach
    void init(){
        conta = new Conta(250.0d, "12323-3",
                new Correntista(12345678900l, "Teste"));
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
}
