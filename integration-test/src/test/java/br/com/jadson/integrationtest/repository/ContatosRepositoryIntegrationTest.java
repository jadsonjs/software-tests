/**
 * 
 */
package br.com.jadson.integrationtest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * ContatosRepositoryIntegrationTest.java
 * 
 * Testes de integração com spring da classe ContatoRepository
 *
 * @RunWith(SpringRunner.class) = permite carregar o contexto de testes do spring para executar os testes
 * @DataJpaTest =  Configura um banco de dados EM MEMORIA para ser usado nos testes
 * @AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2) = Use H2 embarcado em memoria como o banco de dado dos testes
 * 
 * @author  - 
 * @version 1.0
 * @since 12 de dez de 2018
 *
 * <p><i>This program is distributed without any warranty and it
 * can be freely redistributed for research, classes or private studies, 
 * since the copyright notices are not removed.</i></p>
 *
 */
//@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ContatosRepositoryIntegrationTest {

	@Autowired
	private ContatoRepository contatoRepository;

//	@Rule
//	public ExpectedException expectedException = ExpectedException.none();
//
//	private Contato contato;
//
//	@Before
//	public void start() {
//		contato = new Contato("Chefe", "0y", "9xxxxxxx9");
//	}
//
//	@Test
//	public void saveComDddNuloDeveLancarException() throws Exception {
//		expectedException.expect(ConstraintViolationException.class);
//		expectedException.expectMessage("O DDD deve ser preenchido");
//
//		contato.setDdd(null);
//		contatoRepository.save(contato);
//	}
//
//	@Test
//	public void saveComTelefoneNuloDeveLancarException() throws Exception {
//		expectedException.expect(ConstraintViolationException.class);
//		expectedException.expectMessage("O Telefone deve ser preenchido");
//
//		contato.setTelefone(null);
//		contatoRepository.save(contato);
//	}
//
//	@Test
//	public void saveComNomeNuloDeveLancarException() throws Exception {
//		expectedException.expect(ConstraintViolationException.class);
//		expectedException.expectMessage("O Nome deve ser preenchido");
//
//		contato.setNome(null);
//		contatoRepository.save(contato);
//	}
//
//	@Test
//	public void saveDeveSalvarContato() {
//		contatoRepository.save(contato);
//		List<Contato> contatos = contatoRepository.findAll();
//		Assert.assertEquals(1, contatos.size());
//		contatoRepository.deleteAll();
//	}
//
//	@Test
//	public void deleteByIdDeveRemoverContato() {
//		contatoRepository.save(contato);
//		List<Contato> contatos = contatoRepository.findAll();
//		Assert.assertEquals(1, contatos.size());
//
//		contatoRepository.deleteById(contato.getId());
//		List<Contato> resultado = contatoRepository.findAll();
//		Assert.assertEquals(0, resultado.size());
//	}
	
	
}
