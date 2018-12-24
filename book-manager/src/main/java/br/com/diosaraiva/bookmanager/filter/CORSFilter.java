package br.com.diosaraiva.bookmanager.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

//AJAX para multiplos dominios simultaneos
public class CORSFilter extends OncePerRequestFilter {

	private final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		LOG.info("Adding CORS Headers ........................");

		response.setHeader("Access-Control-Allow-Origin", "*");

		//Tipos permitidos: "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else { 
			filterChain.doFilter(request, response);
		}
	}
}