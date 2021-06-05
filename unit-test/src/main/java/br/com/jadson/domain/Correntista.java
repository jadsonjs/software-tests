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
 * unit-test
 * br.com.jadson.domain
 * Correntista
 * 02/06/21
 */
package br.com.jadson.domain;

import br.com.jadson.exception.NegocioException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * Jadson Santos - jadsonjs@gmail.com
 */
public class Correntista {

    @NotNull(message = "O correntista deve ter um nome")
    @Min(value = 10, message = "O nome do correntista deve ter no mínimo 10 caracteres.")
    @Max(value = 100, message = "O nome do correntista deve ter no máximo 100 caracteres.")
    private String nome;

    @NotNull(message = "O correntista deve ter uma conta")
    private Conta conta;

    /**
     * Evitar isso no seu código
     * @return
     */
    public boolean validate(){
        if(nome == null)
            throw new NegocioException("O correntista deve ter um nome");
        else {
            if (nome.length() < 10)
                throw new NegocioException("O nome do correntista deve ter no mínimo 10 caracteres.");
            if (nome.length() > 100)
                throw new NegocioException("O nome do correntista deve ter no máximo 100 caracteres.");
        }
        if(conta == null)
            throw new NegocioException("O correntista deve ter uma conta");
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
