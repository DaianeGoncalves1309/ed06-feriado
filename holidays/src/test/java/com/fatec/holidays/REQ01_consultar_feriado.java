package com.fatec.holidays;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

class REQ01_consultar_feriado {

	@Test
	void ct01_consulta_feriado_com_sucessotest() {

		String baseUrl = "https://api.invertexto.com/v1/holidays/2023?token=5385|TgYM9f2efUloFovkPnQvN6LDMQetXsrP&state=GO";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		record Feriado(String date, String name, String type, String level) {
		}
		;
		HttpEntity request = new HttpEntity<>(headers);
		// make an HTTP GET request with headers
		ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);
		// validar o status retornado
		assertEquals("200 OK", response.getStatusCode().toString());
		// validar o body
		System.out.println(response.getBody());
		// validar o headers retornado
		assertEquals("application/json", response.getHeaders().getContentType().toString());
	}

	@Test
	void ct02_cosulta_feriados_com_autorizacao_invalida() {
		ResponseEntity<String> response = null;
		String baseUrl = "https://api.invertexto.com/v1/holidays/2023?token=5385|TgYM9f2efUloFovkPnQvN6LDMQetXsrP&state=GO";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		record Feriado(String date, String name, String type, String level) {
		}
		;
		HttpEntity request = new HttpEntity<>(headers);
		try {
			// make an HTTP GET request with headers
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);
		} catch (HttpClientErrorException e) {
			// validar o status retornado
			assertEquals("401 UNAUTHORIZED", response.getStatusCode().toString());
		}

	 }
	
	public void covertedUTF8(String str) {
		Gson gson = new Gson();
		try {
			//converte string para byte utf8
			String listaa = str;
			byte[] listab = listaa.getBytes("UTF-8");
			String str2 = new String (listab, "UTF-8");
			//converte gson para objeto java array lista de feriados
			record Feriado(String date, String name, String type, String level) {}
			Feriado[] lista = gson.fromJson(str, Feriado[].class);
			System.out.println(lista[0]);
			assertEquals(17, lista.length);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
	}
	@Test
	void ct03_cosulta_feriados_com_ano_invalido() {
		ResponseEntity<String> response = null;
		String baseUrl = "https://api.invertexto.com/v1/holidays/?token=5385|TgYM9f2efUloFovkPnQvN6LDMQetXsrP&state=GO";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		record Feriado(String date, String name, String type, String level) {
		}
		;
		HttpEntity request = new HttpEntity<>(headers);
		try {
			// make an HTTP GET request with headers
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, request, String.class);
		} catch (HttpClientErrorException e) {
			// validar o status retornado
			assertEquals("404 NOT_FOUND", e.getStatusCode().toString());
		}

	 }
}
