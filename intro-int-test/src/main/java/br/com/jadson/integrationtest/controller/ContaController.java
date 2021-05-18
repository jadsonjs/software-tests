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
package br.com.jadson.integrationtest.controller;

import br.com.jadson.integrationtest.exception.NegocioException;
import br.com.jadson.integrationtest.model.Conta;
import br.com.jadson.integrationtest.model.Correntista;
import br.com.jadson.integrationtest.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ContaController.java
 *
 */
@Controller
public class ContaController {

	@Autowired
	private ContaService contaService;

	/**
	 * Open a new bank account
	 * @param saldo
	 * @param numero
	 * @param cpf
	 * @param nome
	 * @throws NegocioException
	 */
	public void abrirConta(Double saldo, String numero, Long cpf, String nome) throws NegocioException {
		Conta conta = new Conta(saldo, numero, new Correntista(cpf, nome));
		contaService.abrirConta(conta);
	}

	
}
