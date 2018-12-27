package br.com.diosaraiva.bookmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan("br.com.diosaraiva.bookmanager")
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}

}