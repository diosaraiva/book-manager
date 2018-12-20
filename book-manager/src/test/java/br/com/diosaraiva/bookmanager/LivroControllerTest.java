package br.com.diosaraiva.bookmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.diosaraiva.bookmanager.controller.LivroController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivroControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private LivroController livroController;

	@Test
	public void testarContextoLivro() throws Exception {
		assertThat(livroController).isNotNull();
	}

	@Test
	public void testarAdicionarLivro() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/livros/novo"))
		.andExpect(MockMvcResultMatchers.model().attributeExists("isbn"))	
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testarSelecionarLivroPorISBN() throws Exception {
		//
	}

	@Test
	public void testarListarLivros() throws Exception {
		//
	}
	
	@Test
	public void testarAtualizarLivro() throws Exception {
		//
	}
	
	@Test
	public void testarRemoverLivro() throws Exception {
		//
	}
	
}