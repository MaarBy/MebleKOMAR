package com.marko.MebleOnLine.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marko.MebleOnLine.data.Uzytkownik;
import com.marko.MebleOnLine.repository.UzytkownikRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UzytkownikRepository uzytkownikRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Uzytkownik> uzytkownicy = uzytkownikRepository.findByEmail(email);

		if (!uzytkownicy.isEmpty()) {
			Uzytkownik uzytkownik = uzytkownicy.get(0);
			log.info("loadUserByEmail() : {}", uzytkownik.getEmail());
			return new MyUserDetails(uzytkownik);
		} else {
			throw new UsernameNotFoundException("Uzytkownika nie znaleziono.");
		}
	}
}