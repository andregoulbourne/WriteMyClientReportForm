package com.andre;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CorsFilter is a filter that adds CORS headers to the HTTP response.
 * It allows cross-origin requests from any origin and supports various HTTP methods and headers.
 */
@Component
@WebFilter("/*")
public class CorsFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
		chain.doFilter(request, response);
		
	}

}