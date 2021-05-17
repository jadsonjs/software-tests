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
 * unit-test
 * br.com.jadson.domain
 * ContaTestMockito
 * 17/05/21
 */
package br.com.jadson.domain;

import br.com.jadson.exception.NegocioException;
import br.com.jadson.repository.CustomContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
@ExtendWith(MockitoExtension.class)
public class ContaTestMockito {

    @Mock
    CustomContaRepository repository;

    @Test
    void testaSacarContaComSaldo(){
        Conta conta = new Conta(100.0);
        conta.setRepository(repository);
        Assertions.assertEquals(80.0d, conta.sacar(20.0));
    }

    @Test
    void testaSacarContaComSaldoLimiteChequeEspecial(){
        Conta conta = new Conta(100.0);
        conta.setRepository(repository);

        Mockito.when(   repository.findLimiteChequeEspecial(conta)   ).thenReturn(1000.0d);

        Assertions.assertEquals(-400.0d, conta.sacar(500.0));
    }

    @Test
    void testaSacarContaComSaldoLimiteChequeEspecialUltrapassado(){
        Conta conta = new Conta(100.0);
        conta.setRepository(repository);

        Mockito.when(   repository.findLimiteChequeEspecial(conta)   ).thenReturn(300.0d);

        Exception ex = Assertions.assertThrows(NegocioException.class,
                () -> conta.sacar(500.0));

        Assertions.assertEquals("Limite do Cheque Especial ultrapassado.", ex.getMessage());

    }

}


