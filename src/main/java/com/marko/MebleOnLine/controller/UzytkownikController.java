package com.marko.MebleOnLine.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marko.MebleOnLine.data.Uzytkownik;
import com.marko.MebleOnLine.repository.AuthorityRepository;
import com.marko.MebleOnLine.repository.KoszykRepository;
import com.marko.MebleOnLine.repository.UzytkownikRepository;

@Controller
public class UzytkownikController {
	
	@Autowired
	UzytkownikRepository uzytkownikRepository;

	@Autowired
	KoszykRepository koszykRepository;
		
	@Autowired
	AuthorityRepository authorityRepository;
	
	
	@GetMapping("/uzytkownicy")
	public String uzytkownicy(Model model) {
		List<Uzytkownik> uzytkownicy = uzytkownikRepository.findAll();
		model.addAttribute("uzytkownicy", uzytkownicy);
		return "uzytkownicy";
	}
	
	@PostMapping("/dodajUzytkownik")
	public String dodajUzytkownik(@ModelAttribute Uzytkownik uzytkownik) {
		uzytkownikRepository.save(uzytkownik);
		return "redirect:/uzytkownicy";
	}
	
	@GetMapping("/dodajUzytkownik")
	public String dodajUzytkownik() {
		return "dodajUzytkownik";
	}
	
	@PostMapping("/usunUzytkownik")
	public String usunUzytkownik(@ModelAttribute Uzytkownik uzytkownik) {
//		uzytkownik.setAuthorities(null);
//		koszykRepository.deleteById(uzytkownik.getId() -1);


		uzytkownikRepository.deleteById(uzytkownik.getId());
		return "redirect:/uzytkownicy";
	}
	
	@GetMapping("/edytujUzytkownik/{id}")
	public String edytujUzytkownik(@PathVariable(name = "id") String id, Model model) {
		Optional<Uzytkownik> uzytkownik = uzytkownikRepository.findById(Integer.parseInt(id));
		if(uzytkownik.isPresent()) {
			model.addAttribute("uzytkownik", uzytkownik.get());
			return "edytujUzytkownik";
		}
		return "redirect:/uzytkownicy";
	}

	@PostMapping("/edytujUzytkownik")
	public String edytujUzytkownik(@ModelAttribute Uzytkownik uzytkownik) {
		uzytkownikRepository.save(uzytkownik);
		return "redirect:/uzytkownicy";
	}
	

		
}
