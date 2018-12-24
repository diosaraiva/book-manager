package br.com.diosaraiva.bookmanager;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.model.LivroExtenso;
import br.com.diosaraiva.bookmanager.service.LivroService;
import br.com.diosaraiva.bookmanager.utils.LivroUtil;
import br.com.diosaraiva.bookmanager.utils.StringUtil;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class LivroControllerUnitTest {

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

	@Test
	public void testarAdicionarLivro() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.exists(livro)).thenReturn(false);
		doNothing().when(livroService).adicionarLivro(livro);

		mockMvc.perform(
				post("/livros/novo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(StringUtil.asJsonString(livro)))
		.andExpect(status().isCreated())
		.andExpect(header().string("location", containsString("/livros/1")));

		verify(livroService, times(1)).exists(livro);
		verify(livroService, times(1)).adicionarLivro(livro);
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarAdicionarLivroFail_404_not_found() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.exists(livro)).thenReturn(true);

		mockMvc.perform(
				post("/livros/novo")
				.contentType(MediaType.APPLICATION_JSON)
				.content(StringUtil.asJsonString(livro)))
		.andExpect(status().isConflict());

		verify(livroService, times(1)).exists(livro);
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarSelecionarLivroPorISBN() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.selecionarLivroPorISBN(1)).thenReturn(livro);

		mockMvc.perform(get("/lista/{isbn}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.isbn", is(1)))
		.andExpect(jsonPath("$.titulo", is("Titulo 1")));

		verify(livroService, times(1)).selecionarLivroPorISBN(1);
		verifyNoMoreInteractions(livroService);
	}
	
	@Test
	public void testarSelecionarLivroExtensoPorISBN() throws Exception {

		LivroExtenso livroExtenso = 
				new LivroExtenso(LivroUtil.criarCenarioTesteLivro());

		when(livroService.selecionarLivroExtensoPorISBN(1)).thenReturn(livroExtenso);

		mockMvc.perform(get("/livros/{isbn}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.isbn", is(1)))
		.andExpect(jsonPath("$.titulo", is("Titulo 1")));

		verify(livroService, times(1)).selecionarLivroExtensoPorISBN(1);
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarSelecionarLivroExtensoPorISBNFail_404_not_found() throws Exception {
		when(livroService.selecionarLivroPorISBN(1)).thenReturn(null);

		mockMvc.perform(get("/livros/{isbn}", 1))
		.andExpect(status().isNotFound());

		verify(livroService, times(1)).selecionarLivroExtensoPorISBN(1);
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarSelecionarLivroPorISBNFail_404_not_found() throws Exception {
		when(livroService.selecionarLivroPorISBN(1)).thenReturn(null);

		mockMvc.perform(get("/lista/{isbn}", 1))
		.andExpect(status().isNotFound());

		verify(livroService, times(1)).selecionarLivroPorISBN(1);
		verifyNoMoreInteractions(livroService);
	}
	
	@Test
	public void testarListarLivros() throws Exception {

		List<Livro> listaLivros = LivroUtil.criarCenarioTesteLista();

		when(livroService.listarLivros()).thenReturn(listaLivros);

		mockMvc.perform(get("/lista"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].isbn", is(1)));

		verify(livroService, times(1)).listarLivros();
		verifyNoMoreInteractions(livroService);

	}

	@Test
	public void testarListarLivrosExtenso() throws Exception {

		List<LivroExtenso> listaLivrosExtenso = 
				LivroUtil.ConverteListaLivroParaListaLivroExtenso
				(LivroUtil.criarCenarioTesteLista());

		when(livroService.listarLivrosExtenso()).thenReturn(listaLivrosExtenso);

		mockMvc.perform(get("/livros"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].isbn", is(1)));

		verify(livroService, times(1)).listarLivrosExtenso();
		verifyNoMoreInteractions(livroService);

	}

	@Test
	public void testarAtualizarLivro() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.selecionarLivroPorISBN(livro.getIsbn())).thenReturn(livro);
		doNothing().when(livroService).atualizarLivro(livro);

		mockMvc.perform(
				put("/livros/{isbn}", livro.getIsbn())
				.contentType(MediaType.APPLICATION_JSON)
				.content(StringUtil.asJsonString(livro)))
		.andExpect(status().isOk());

		verify(livroService, times(1)).selecionarLivroPorISBN(livro.getIsbn());
		verify(livroService, times(1)).atualizarLivro(livro);
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarAtualizarLivroFail_404_not_found() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.selecionarLivroPorISBN(livro.getIsbn())).thenReturn(null);

		mockMvc.perform(
				put("/livros/{isbn}", livro.getIsbn())
				.contentType(MediaType.APPLICATION_JSON)
				.content(StringUtil.asJsonString(livro)))
		.andExpect(status().isNotFound());

		verify(livroService, times(1)).selecionarLivroPorISBN(livro.getIsbn());
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarRemoverLivro() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.selecionarLivroPorISBN(livro.getIsbn())).thenReturn(livro);
		doNothing().when(livroService).removerLivro(livro.getIsbn());

		mockMvc.perform(
				delete("/livros/{isbn}", livro.getIsbn()))
		.andExpect(status().isOk());

		verify(livroService, times(1)).selecionarLivroPorISBN(livro.getIsbn());
		verify(livroService, times(1)).removerLivro(livro.getIsbn());
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarRemoverLivroFail_404_not_found() throws Exception {

		Livro livro = LivroUtil.criarCenarioTesteLivro();

		when(livroService.selecionarLivroPorISBN(livro.getIsbn())).thenReturn(null);

		mockMvc.perform(
				delete("/livros/{isbn}", livro.getIsbn()))
		.andExpect(status().isNotFound());

		verify(livroService, times(1)).selecionarLivroPorISBN(livro.getIsbn());
		verifyNoMoreInteractions(livroService);
	}

	@Test
	public void testarCabecalhosCORS() throws Exception {
		mockMvc.perform(get("/livros"))
		.andExpect(header().string("Access-Control-Allow-Origin", "*"))
		.andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
		.andExpect(header().string("Access-Control-Allow-Headers", "*"))
		.andExpect(header().string("Access-Control-Max-Age", "3600"));
	}

}