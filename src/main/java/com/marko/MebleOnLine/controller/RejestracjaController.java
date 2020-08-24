package com.marko.MebleOnLine.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.marko.MebleOnLine.data.Authority;
import com.marko.MebleOnLine.data.AuthorityType;
import com.marko.MebleOnLine.data.Koszyk;
import com.marko.MebleOnLine.data.Uzytkownik;
import com.marko.MebleOnLine.repository.AuthorityRepository;
import com.marko.MebleOnLine.repository.KoszykRepository;
import com.marko.MebleOnLine.repository.UzytkownikRepository;

@Controller
public class RejestracjaController {

	@Autowired
	UzytkownikRepository uzytkownikRepository;
	
	@Autowired
	KoszykRepository koszykRepository;
		
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/rejestracja")
	public String produkty() {
		return "rejestracja";
	}
	
	@PostMapping("/rejestracja")
	public String rejestracjaUzytkownika(@ModelAttribute Uzytkownik uzytkownik) {
		uzytkownik.setHaslo(bCryptPasswordEncoder.encode(uzytkownik.getHaslo()));
		Set<Authority> user = authorityRepository.findByName(AuthorityType.ROLE_USER);
		uzytkownik.setAuthorities(user);
		uzytkownikRepository.save(uzytkownik);
		
		Koszyk koszyk = new Koszyk();
		koszyk.setUzytkownik(uzytkownik);
		koszykRepository.save(koszyk);
		
		return "redirect:/logowanie";
	}
	
}
