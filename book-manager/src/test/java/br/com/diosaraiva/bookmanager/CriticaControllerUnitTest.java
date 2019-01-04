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
import br.com.diosaraiva.bookmanager.controller.CriticaController;
import br.com.diosaraiva.bookmanager.service.CriticaServiceImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class CriticaControllerUnitTest {

	private MockMvc mockMvc;

	@Mock
	private CriticaServiceImpl criticaService;

	@InjectMocks
	private CriticaController criticaController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(criticaController)
				.build();
	}
	
	//
	
}
