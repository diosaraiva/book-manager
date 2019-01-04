package br.com.diosaraiva.bookmanager;

import org.junit.Before;
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
import br.com.diosaraiva.bookmanager.controller.AutorController;
import br.com.diosaraiva.bookmanager.service.AutorServiceImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class AutorControllerUnitTest {

	private MockMvc mockMvc;

	@Mock
	private AutorServiceImpl autorService;

	@InjectMocks
	private AutorController autorController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(autorController)
				.build();
	}
	
	//
	
}
