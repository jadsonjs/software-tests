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
 *
 * software-tests
 * br.com.jadson.test
 * Conta
 * 14/05/21
 */
package br.com.jadson.domain;

import br.com.jadson.exception.NegocioException;
import br.com.jadson.repository.CustomContaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Positive;

/**
 * Jadson Santos - jadsonjs@gmail.com
 */

public class Conta {

    private Long id;

    private Double saldo = 0.0d;

    @Transient
    @Autowired
    CustomContaRepository repository;


    public Conta(Double saldo){
        if(saldo == null || saldo < 0 )
            throw new IllegalArgumentException("Saldo Inválido");
        this.saldo = saldo;
    }



    public boolean temSaldo(){
        return saldo > 0 ;
    }

    public double sacar(double valor){
        if(valor < 0)
            throw new IllegalArgumentException("Não pode sacar um valor negativo.");

        double limite = repository.findLimiteChequeEspecial(this);

        saldo = saldo - valor;

        if(saldo + limite < 0)
            throw new NegocioException("Limite do Cheque Especial ultrapassado.");

        repository.atualizarSaldo(this);
        return saldo;
    }

    public double depositar(Double valor) {
        if(valor < 0)
            throw new IllegalArgumentException("Não é possível depositar um valor negativo.");

        saldo = saldo + valor;
        repository.atualizarSaldo(this);
        return saldo;
    }


    public void setRepository(CustomContaRepository repository) {
        this.repository = repository;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Long getId(){
        return id;
    }


}
