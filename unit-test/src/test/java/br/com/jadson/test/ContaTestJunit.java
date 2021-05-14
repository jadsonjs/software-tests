package br.com.jadson.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}

