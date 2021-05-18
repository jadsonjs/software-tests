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
 */
package br.com.jadson.mvctest.mvctest.service;


import br.com.jadson.mvctest.mvctest.exception.NegocioException;
import br.com.jadson.mvctest.mvctest.model.Conta;
import br.com.jadson.mvctest.mvctest.model.Correntista;
import br.com.jadson.mvctest.mvctest.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ContaServiceImpl.java
 *
 */
@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Transactional
	@Override
	public void abrirConta(Conta conta) {
		if(conta == null)
			throw new IllegalArgumentException("Uma conta deve ser passada");

		correntistaPossuiDebitos(conta.getCorrentista());
		contaRepository.save(conta);
	}

	private boolean correntistaPossuiDebitos(Correntista correntista) {
		List<Conta> contas = contaRepository.findByCpfCorrentista(correntista.getCpf());
		for (Conta conta : contas){
			if(conta.isNegativada())
				throw new NegocioException("Não é possível abrir conta. Correntista possui débitos");
		}
		return false;
	}
}



