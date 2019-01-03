package br.com.diosaraiva.bookmanager.utils;

import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String gerarTituloLivro (){

		String[] Comeco = { "Amanhecer", "Despertar", "Jornada", "Descoberta", "A História",
				"A Busca", "A Casa", "Experiências", "Contatos", "O Diário", "O Assassinato",
				"A Cura" };
		
		String[] Meio = { "sem", "da", "de", "com", "contra" };
		
		String[] Fim = { "Ariel", "Alex", "Pântano", "Salvação", "Esperança", "Morte",
				"Vida", "Felicidade", "Batalha", "Fogo", "Gelo" };

		Random rand = new Random();

		return Comeco[rand.nextInt(Comeco.length)] + " " +
				Meio[rand.nextInt(Meio.length)]+ " " +
				Fim[rand.nextInt(Fim.length)];

	}

}
