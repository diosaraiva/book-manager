package br.com.diosaraiva.bookmanager;

import br.com.diosaraiva.bookmanager.config.WebConfig;
import br.com.diosaraiva.bookmanager.model.Autor;
import br.com.diosaraiva.bookmanager.model.Critica;
import br.com.diosaraiva.bookmanager.model.Editora;
import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.service.LivroService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class LivroControllerIntegrationTest {

	private static final String BASE_URI = "http://localhost:8080/livros";
	private static final int UNKNOWN_ID = Integer.MAX_VALUE;

	@Autowired
	private RestTemplate template;

	//Cria objetos para Testes
	private List<Livro> CriarCenarioTesteLista() {

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

	private Livro CriarCenarioTesteLivro() {

		Set<Critica> criticas = new HashSet<Critica>();
		criticas.add(new Critica("Critico 1", 5, "Texto 1"));
		criticas.add(new Critica("Critico 2", 0, "Texto 2"));

		Editora editora = new Editora("Editora 1", "http://localhost:8080/");

		Set<Autor> autores = new HashSet<Autor>();
		autores.add(new Autor("Diogo 1", "Brasileiro", new Date(), null));
		autores.add(new Autor("Autor 2", "Brasileiro", new Date(), null));

		return new Livro(1, "Titulo 1", new Date(), 10.00, "Sinopse 1", autores, 
				editora, criticas);
	}

	@Test
	public void testarAdicionarLivroIT(){
		Livro livro = CriarCenarioTesteLivro();
		URI location = template.postForLocation(BASE_URI, livro, Livro.class);
		assertThat(location, notNullValue());
	}

	@Test
	public void testarAdicionarLivroITFail(){

		Livro livro = CriarCenarioTesteLivro();

		try {
			URI location = template.postForLocation(BASE_URI + "/novo", livro, Livro.class);
			fail("should return 409 conflict");
		} catch (HttpClientErrorException e){
			assertThat(e.getStatusCode(), is(HttpStatus.CONFLICT));
			validateCORSHttpHeaders(e.getResponseHeaders());
		}
	}

	@Test
	public void testarListarLivrosIT(){
		ResponseEntity<Livro[]> response = template.getForEntity(BASE_URI, Livro[].class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		validateCORSHttpHeaders(response.getHeaders());
	}

	@Test
	public void testarSelecionarLivroPorISBNIT(){
		ResponseEntity<Livro> response = template.getForEntity(BASE_URI + "/1", Livro.class);
		Livro livro = response.getBody();
		assertThat(livro.getIsbn(), is(1));
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		validateCORSHttpHeaders(response.getHeaders());
	}

	@Test
	public void testarSelecionarLivroPorISBNITFail(){
		try {
			ResponseEntity<Livro> response = template.getForEntity(BASE_URI + "/" + UNKNOWN_ID, Livro.class);
			fail("should return 404 not found");
		} catch (HttpClientErrorException e){
			assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
			validateCORSHttpHeaders(e.getResponseHeaders());
		}
	}

	@Test
	public void testarAtualizarLivroIT(){
		Livro livro = CriarCenarioTesteLivro();
		template.put(BASE_URI + "/" + livro.getIsbn(), livro);
	}

	@Test
	public void testarAtualizarLivroITFail(){
		Livro livro = CriarCenarioTesteLivro();
		try {
			template.put(BASE_URI + "/" + livro.getIsbn(), livro);
			fail("should return 404 not found");
		} catch (HttpClientErrorException e){
			assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
			validateCORSHttpHeaders(e.getResponseHeaders());
		}
	}

	@Test
	public void testarRemoverLivroIT(){
		template.delete(BASE_URI + "/" + getLastUser().getIsbn());
	}

	@Test
	public void testarRemoverLivroFail(){
		try {
			template.delete(BASE_URI + "/" + UNKNOWN_ID);
			fail("should return 404 not found");
		} catch (HttpClientErrorException e){
			assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
			validateCORSHttpHeaders(e.getResponseHeaders());
		}
	}

	private Livro getLastUser(){
		ResponseEntity<Livro[]> response = template.getForEntity(BASE_URI, Livro[].class);
		Livro[] livros = response.getBody();
		return livros[livros.length - 1];
	}

	public void validateCORSHttpHeaders(HttpHeaders headers){
		assertThat(headers.getAccessControlAllowOrigin(), is("*"));
		assertThat(headers.getAccessControlAllowHeaders(), hasItem("*"));
		assertThat(headers.getAccessControlMaxAge(), is(3600L));
		assertThat(headers.getAccessControlAllowMethods(), hasItems(
				HttpMethod.GET,
				HttpMethod.POST,
				HttpMethod.PUT,
				HttpMethod.OPTIONS,
				HttpMethod.DELETE));
	}
}