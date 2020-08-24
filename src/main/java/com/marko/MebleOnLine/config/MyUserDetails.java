package com.marko.MebleOnLine.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.marko.MebleOnLine.data.Uzytkownik;

public class MyUserDetails implements UserDetails {
	private Uzytkownik uzytkownik;

	public MyUserDetails(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return uzytkownik.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName().toString()))
				.collect(Collectors.toList());
	}

	public int getId() {
		return uzytkownik.getId();
	}

	@Override
	public String getPassword() {
		return uzytkownik.getHaslo();
	}

	@Override
	public String getUsername() {
		return uzytkownik.getEmail();
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

	public Uzytkownik getUserDetails() {
		return uzytkownik;
	}
}
