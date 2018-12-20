package br.com.diosaraiva.bookmanager;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.diosaraiva.bookmanager.config.WebConfig;
import br.com.diosaraiva.bookmanager.controller.LivroController;
import br.com.diosaraiva.bookmanager.filter.CORSFilter;
import br.com.diosaraiva.bookmanager.model.Autor;
import br.com.diosaraiva.bookmanager.model.Critica;
import br.com.diosaraiva.bookmanager.model.Editora;
import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.service.LivroService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class LivroControllerUnitTest {

	private static final int UNKNOWN_ID = Integer.MAX_VALUE;

	private MockMvc mockMvc;

	@Mock
	private LivroService livroService;

	@InjectMocks
	private LivroController livroController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(livroController)
				.addFilters(new CORSFilter())
				.build();
	}

	//Cria objetos para Testes
	private List<Livro> CriarCenarioTeste() {

		Set<Critica> criticas = new HashSet<Critica>();
		criticas.add(new Critica("Critico 1", 5, "Texto 1"));
		criticas.add(new Critica("Critico 2", 0, "Texto 2"));

		Editora editora = new Editora("Editora 1", "http://localhost:8080/");

		Set<Autor> autores = new HashSet<Autor>();
		autores.add(new Autor("Diogo 1", "Brasileiro", new Date(), null));
		autores.add(new Autor("Autor 2", "Brasileiro", new Date(), null));

		Set<Livro> livros = new HashSet<Livro>();
		livros.add(new Livro(1, "Titulo 1", new Date(), 10.00, "Sinopse 1", autores, 
				editora, criticas));

		List<Livro> listaLivros = new ArrayList<Livro>();
		listaLivros.addAll(livros);

		return listaLivros;
	}

	@Test
	public void testarAdicionarLivro() throws Exception {
		//
	}

	@Test
	public void testarSelecionarLivroPorISBN() throws Exception {
		//
	}

	@Test
	public void testarListarLivros() throws Exception {

		List<Livro> listaLivros = CriarCenarioTeste();

		when(livroService.listarLivros()).thenReturn(listaLivros);

		mockMvc.perform(get("/livros"))
		.andExpect(status().isOk()) //ERRO A PARTIR DAQUI PELO BREAKPOINT
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].username", is("John Snow")));

		verify(livroService, times(1)).listarLivros();
		verifyNoMoreInteractions(livroService);
		
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