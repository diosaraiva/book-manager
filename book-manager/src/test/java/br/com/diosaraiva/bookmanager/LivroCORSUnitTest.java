package br.com.diosaraiva.bookmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.diosaraiva.bookmanager.config.WebConfig;
import br.com.diosaraiva.bookmanager.controller.LivroController;
import br.com.diosaraiva.bookmanager.filter.CORSFilter;
import br.com.diosaraiva.bookmanager.service.LivroService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class LivroCORSUnitTest {

	//Usa o Mockito para simular a camada de serviço. 
	//Garante o isolamento do teste do Controller.
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
	public void testarCabecalhosCORS() throws Exception {
		mockMvc.perform(get("/livros"))
		.andExpect(header().string("Access-Control-Allow-Origin", "*"))
		.andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
		.andExpect(header().string("Access-Control-Allow-Headers", "*"))
		.andExpect(header().string("Access-Control-Max-Age", "3600"));
	}

}