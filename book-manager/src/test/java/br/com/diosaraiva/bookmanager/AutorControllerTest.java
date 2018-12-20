package br.com.diosaraiva.bookmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.diosaraiva.bookmanager.controller.AutorController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutorControllerTest {

	@Autowired
	private AutorController autorController;

	@Test
	public void testarContextoAutor() throws Exception {
		assertThat(autorController).isNotNull();
	}
	
}