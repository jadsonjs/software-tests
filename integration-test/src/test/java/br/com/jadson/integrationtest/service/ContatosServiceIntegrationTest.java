/**
 * 
 */
package br.com.jadson.integrationtest.service;

import br.com.jadson.integrationtest.model.Contato;
import br.com.jadson.integrationtest.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ContatosServiceIntegrationTest.java
 *
 *
 * @SpringBootTest = carrega as configuracoes do spring boot. Deve ser usado quando estamos testando funcionalidades do spring boot 
 * <p></p>
 *
 * @author  - 
 * @version 1.0
 * @since 16 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ContatosServiceIntegrationTest {

	@Autowired
	private ContatoService contatoService;

	@Autowired
	private ContatoRepository contatoRepository;

	private Contato contato;

	//@Rule
	//public ExpectedException expectedException = ExpectedException.none();

//	@Before
//	public void start() {
//		contato = new Contato("Chefe", "0y", "9xxxxxxxx9");
//	}
//
//	@Test
//	public void inserirComDddNuloLancaException() throws ContatoException {
//		expectedException.expect(ContatoException.class);
//		expectedException.expectMessage("O DDD deve ser preenchido");
//
//		contato.setDdd(null);
//		contatoService.inserir(contato);
//	}
//
//	@Test
//	public void inserirComTelefoneNuloLancaException() throws ContatoException {
//		expectedException.expect(ContatoException.class);
//		expectedException.expectMessage("O Telefone deve ser preenchido");
//
//		contato.setTelefone(null);
//		contatoService.inserir(contato);
//	}
//
//	@Test
//	public void inserirComNomeNuloLancaException() throws ContatoException {
//		expectedException.expect(ContatoException.class);
//		expectedException.expectMessage("O Nome deve ser preenchido");
//
//		contato.setNome(null);
//		contatoService.inserir(contato);
//	}
//
//	@Test
//	public void inserirDeveSalvarContato() throws ContatoException {
//		contatoService.inserir(contato);
//
//		List<Contato> contatos = contatoRepository.findAll();
//		Assert.assertEquals(1, contatos.size());
//		contatoRepository.deleteAll();
//	}
//
//	@Test
//	public void removerDeveRemoverContato() {
//		contatoRepository.save(contato);
//		List<Contato> contatos = contatoRepository.findAll();
//		Assert.assertEquals(1, contatos.size());
//
//		contatoService.remover(contato.getId());
//		List<Contato> resultado = contatoRepository.findAll();
//		Assert.assertEquals(0, resultado.size());
//	}

}