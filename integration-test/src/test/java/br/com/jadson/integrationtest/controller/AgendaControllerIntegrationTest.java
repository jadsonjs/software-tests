/**
 * 
 */
package br.com.jadson.integrationtest.controller;

import br.com.jadson.integrationtest.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * AgendaControllerIntegrationTest.java
 *
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
// @RunWith(SpringRunner.class)
@SpringBootTest
public class AgendaControllerIntegrationTest {

	/**
	 * @MockBean Cria Mock para objetos do Spring
	 * 
	 * Spring usa usa esse mock quando for injetar o contatoRepository no contatoService
	 */
	@MockBean
	private ContatoRepository contatoRepository;

	@Autowired
	private AgendaController agendaController;

//	@Rule
//	public ExpectedException expectedException = ExpectedException.none();
//
//	private String nome = "Chefe";
//
//	private String ddd = "0y";
//
//	private String telefone = "9xxxxxxx9";
//
//	@Test
//	public void inserirRegistroComDddNuloDeveLancarException() throws ContatoException {
//		expectedException.expect(ContatoException.class);
//		expectedException.expectMessage("O DDD deve ser preenchido");
//
//		Mockito.when(contatoRepository.save((Contato)Mockito.any()))
//		.thenThrow(new ConstraintViolationException("O DDD deve ser preenchido",null));
//
//
//
//		agendaController.inserirRegistro(nome, null, telefone);
//	}
//
//	@Test
//	public void inserirRegistroComTelefoneNuloDeveLancarException() throws ContatoException {
//		expectedException.expect(ContatoException.class);
//		expectedException.expectMessage("O Telefone deve ser preenchido");
//
//		Mockito.when(contatoRepository.save((Contato)Mockito.any()))
//		.thenThrow(new ConstraintViolationException("O Telefone deve ser preenchido",null));
//
//		agendaController.inserirRegistro(nome, ddd, null);
//	}
//
//	@Test
//	public void inserirRegistroComNomeNuloDeveLancarException() throws ContatoException {
//		expectedException.expect(ContatoException.class);
//		expectedException.expectMessage("O Nome deve ser preenchido");
//
//		Mockito.when(contatoRepository.save((Contato)Mockito.any()))
//		.thenThrow(new ConstraintViolationException("O Nome deve ser preenchido",null));
//
//		agendaController.inserirRegistro(null, ddd, telefone);
//	}
//
//	@Test
//	public void inserirRegistroDeveSalvarContato() throws ContatoException {
//		agendaController.inserirRegistro(nome, ddd, telefone);
//		Mockito.verify(contatoRepository,Mockito.times(1)).save(new Contato(nome, ddd, telefone));
//	}
//
//	@Test
//	public void removerRegistroDeveRemoverContato() {
//		agendaController.removerRegistro(1L);
//		Mockito.verify(contatoRepository,Mockito.times(1)).deleteById(1L);
//	}
	
}
