package br.com.diosaraiva.bookmanager;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.URI;

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

import br.com.diosaraiva.bookmanager.config.WebConfig;
import br.com.diosaraiva.bookmanager.model.Livro;
import br.com.diosaraiva.bookmanager.utils.LivroUtil;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class LivroControllerIntegrationTest {

	private static final String BASE_URI = "http://localhost:8080/livros";
	private static final int UNKNOWN_ID = Integer.MAX_VALUE;

	@Autowired
	private RestTemplate template;

	@Test
	public void testarAdicionarLivroIT(){
		Livro livro = LivroUtil.criarCenarioTesteLivro();
		URI location = template.postForLocation(BASE_URI, livro, Livro.class);
		assertThat(location, notNullValue());
	}

	@Test
	public void testarAdicionarLivroITFail(){

		Livro livro = LivroUtil.criarCenarioTesteLivro();

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
		Livro livro = LivroUtil.criarCenarioTesteLivro();
		template.put(BASE_URI + "/" + livro.getIsbn(), livro);
	}

	@Test
	public void testarAtualizarLivroITFail(){
		Livro livro = LivroUtil.criarCenarioTesteLivro();
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