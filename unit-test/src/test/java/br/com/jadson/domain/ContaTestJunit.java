package br.com.jadson.domain;

import br.com.jadson.mocks.CustomContaJDBCRepositoryMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContaTestJunit {

    @Test
    void testaContaComSaldo(){
        Conta conta = new Conta(10.0d);
        Assertions.assertTrue(conta.temSaldo());
    }

    @Test
    void testaContaSemSaldo(){
        Conta conta = new Conta(0.0d);
        Assertions.assertFalse(conta.temSaldo());
    }

    @Test
    void testaContaComSaldoNulo(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Conta(null).temSaldo());
    }


    @Test
    void testaSacarValorNegativo1(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Conta(100.0).sacar(-10.0));
    }

    @Test
    void testaSacarValorNegativo(){
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Conta(100.0).sacar(-10.0));

        Assertions.assertEquals("Não pode sacar um valor negativo.", ex.getMessage());
    }


    @Test
    void testaSacarContaComSaldo(){
        Conta conta = new Conta(100.0);
        conta.setRepository(new CustomContaJDBCRepositoryMock());
        Assertions.assertEquals(80.0d, conta.sacar(20.0));
    }


}

