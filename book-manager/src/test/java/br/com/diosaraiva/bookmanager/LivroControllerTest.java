package br.com.diosaraiva.bookmanager;

import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.diosaraiva.bookmanager.model.Autor;
import br.com.diosaraiva.bookmanager.model.Critica;
import br.com.diosaraiva.bookmanager.model.Editora;
import br.com.diosaraiva.bookmanager.model.Livro;

public class LivroControllerTest {
    public void testarSelecionarLivroPorISBN() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/livro/{isbn}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Livro> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Livro.class, 1);
        Livro livro = responseEntity.getBody();
        System.out.println("Id:"+livro.getIsbn() + 
        		", Titulo:"+livro.getTitulo()
                 +", Data de Publicacao:"+livro.getDataPublicacao() +
                 ", Preco:"+livro.getPreco() +
                 ", Sinopse:"+livro.getSinopse());
        		//falta imprimir autores, criticas, editora
    }
    
	public void testarListarLivros() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/livros";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Livro[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Livro[].class);
        Livro[] livros = responseEntity.getBody();
        for(Livro livro : livros) {
        	System.out.println("Id:"+livro.getIsbn() + 
            		", Titulo:"+livro.getTitulo()
                     +", Data de Publicacao:"+livro.getDataPublicacao() +
                     ", Preco:"+livro.getPreco() +
                     ", Sinopse:"+livro.getSinopse());
            		//falta imprimir autores, criticas, editora
        }
    }
	
    public void testarAdicionarLivro() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/livro/{isbn}";
	    
	    Livro objLivro = new Livro();
	    objLivro.setIsbn((long)12345);
	    objLivro.setTitulo("Teste Unitario");
	    objLivro.setDataPublicacao(new Date());
	    objLivro.setPreco(10.00);
	    objLivro.setSinopse("Sinopse");
	    
	    Set<Autor> autores = new HashSet<Autor>();
	    
	    Autor objAutor = new Autor();
	    objAutor.setNomeAutor("Autor 1");
	    objAutor.setNacionalidade("Virtual");
	    objAutor.setNascimento(new Date());
	    
	    autores.add(objAutor);
    
	    objAutor.setNomeAutor("Autor 2");
	    
	    autores.add(objAutor);
	    
	    objLivro.setAutores(autores);
	    
	    Editora objEditora = new Editora();
	    objEditora.setNomeEditora("Editora");
	    objEditora.setSite("http://localhost:8080");
	    
	    objLivro.setEditora(objEditora);
	    
	    Set<Critica> criticas = new HashSet<Critica>();
	    
	    Critica objCritica = new Critica();
	    objCritica.setNomeCritico("Critico 1");
	    objCritica.setNota(10);
	    objCritica.setTexto("Funcionando");
	    
	    criticas.add(objCritica);
	    
	    objLivro.setCriticas(criticas);
	    
        HttpEntity<Livro> requestEntity = new HttpEntity<Livro>(objLivro, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    
    public void testarAtualizarLivro() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/livro";

	    Livro objLivro = new Livro();
	    objLivro.setIsbn((long)12345);
	    objLivro.setTitulo("Teste Unitario");
	    objLivro.setDataPublicacao(new Date());
	    objLivro.setPreco(10.00);
	    objLivro.setSinopse("Sinopse");
	    
	    Set<Autor> autores = new HashSet<Autor>();
	    
	    Autor objAutor = new Autor();
	    objAutor.setNomeAutor("Autor 1");
	    objAutor.setNacionalidade("Virtual");
	    objAutor.setNascimento(new Date());
	    
	    autores.add(objAutor);
    
	    objAutor.setNomeAutor("Autor 2");
	    
	    autores.add(objAutor);
	    
	    objLivro.setAutores(autores);
	    
	    Editora objEditora = new Editora();
	    objEditora.setNomeEditora("Editora");
	    objEditora.setSite("http://localhost:8080");
	    
	    objLivro.setEditora(objEditora);
	    
	    Set<Critica> criticas = new HashSet<Critica>();
	    
	    Critica objCritica = new Critica();
	    objCritica.setNomeCritico("Critico 1");
	    objCritica.setNota(10);
	    objCritica.setTexto("Funcionando");
	    
	    criticas.add(objCritica);
	    
	    objLivro.setCriticas(criticas);
	    
        HttpEntity<Livro> requestEntity = new HttpEntity<Livro>(objLivro, headers);
        restTemplate.put(url, requestEntity);
    }
    
    public void testarremoverLivro() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/livro/{isbn}";
        HttpEntity<Livro> requestEntity = new HttpEntity<Livro>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    
    public static void main(String args[]) {
    	LivroControllerTest util = new LivroControllerTest();
        //util.getArticleByIdDemo();
    	//util.addArticleDemo();
    	//util.updateArticleDemo();
    	//util.deleteArticleDemo();
    	util.testarListarLivros();    	
    }    
}
