package br.com.foursales.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.foursales.security.UsuarioSS;

public class UserService {

	public static UsuarioSS authenticated() {
		try {
			return (UsuarioSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
