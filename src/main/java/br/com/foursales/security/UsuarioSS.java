package br.com.foursales.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	private Integer id;

	private String email;

	private String senha;

	private Collection<? extends GrantedAuthority> autorizacoes;

	public UsuarioSS(int id, String email, String senha, Set<String> perfis) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.autorizacoes = perfis.stream().map(x -> new SimpleGrantedAuthority(x)).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
