package br.com.foursales.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.foursales.security.UsuarioSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	// E-mail, senha e perfil passados manualmente pois não implementei o cadastro
	// de Usuário
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if (!"email@gmail.com".equals(email)) {
			throw new UsernameNotFoundException(email);
		}
		Set<String> perfis = new HashSet<>();
		perfis.add("ROLE_ADMIN");
		return new UsuarioSS(1, email, "$2y$12$xZOiYfM7cmTPeuHPDWrqQe0KJnNnfXV60sYoniOC2VnYLHYXJycma", perfis);
	}

}
