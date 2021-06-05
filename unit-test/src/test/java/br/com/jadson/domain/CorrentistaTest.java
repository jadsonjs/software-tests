package br.com.jadson.domain;

import br.com.jadson.exception.NegocioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrentistaTest {

    void testaCorrenitaSemConta() {
        Correntista c = new Correntista();
        c.setNome("João da Silva Pinto");
        try{
            c.validate();
            System.out.println("FAIL");
        }catch (NegocioException ne){
            if(ne.getMessage().equals("O correntista deve ter uma conta")) {
                System.out.println("PASSED");
            }
        }

    }
    void testaCorrenitaSemNome() {
        Correntista c = new Correntista();
        c.setConta(new Conta(100.0d));
        try{
            c.validate();
            System.out.println("FAIL");

        }catch (NegocioException ne){
            if(ne.getMessage().equals("O correntista deve ter um nome")) {
                System.out.println("PASSED");
            }
        }
    }
    void testaCorrenitaNomeMenorQue10() {
        Correntista c = new Correntista();
        c.setNome("José");
        c.setConta(new Conta(100.0d));
        try{
            c.validate();
            System.out.println("FAIL");
        }catch (NegocioException ne){
            if(ne.getMessage().equals("O nome do correntista deve ter no mínimo 10 caracteres.")) {
                System.out.println("PASSED");
            }
        }
    }

    public static void main(String[] args) {
        new CorrentistaTest().testaCorrenitaSemConta();
        new CorrentistaTest().testaCorrenitaSemNome();
        new CorrentistaTest().testaCorrenitaNomeMenorQue10();

    }
}