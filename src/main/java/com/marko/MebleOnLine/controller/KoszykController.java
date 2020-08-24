package com.marko.MebleOnLine.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.marko.MebleOnLine.data.Koszyk;
import com.marko.MebleOnLine.data.Produkt;
import com.marko.MebleOnLine.data.Uzytkownik;
import com.marko.MebleOnLine.repository.KoszykRepository;
import com.marko.MebleOnLine.repository.ProduktRepository;
import com.marko.MebleOnLine.repository.UzytkownikRepository;

@Controller
public class KoszykController {

	@Autowired
	KoszykRepository koszykRepository;

	@Autowired
	UzytkownikRepository uzytkownikRepository;

	@Autowired
	ProduktRepository produktRepository;

//
//	@GetMapping("/koszyk/{id}")
//	public String wyswietlKoszykById(@PathVariable(name = "id") String id, Model model) {
//		Optional<Koszyk> koszyk = koszykRepository.findById(Integer.parseInt(id));
//		if(koszyk.isPresent()) {
//			model.addAttribute("koszyk", koszyk.get());
//			return "wyswietlKoszyk"; 								// wyswietlKoszyk.html
//		}
//		return "redirect:/";
//	}
//	
	@PostMapping("/dodajDoKoszyka")
	public String dodajProdukt(@ModelAttribute Produkt produkt, Principal principal) {

		String email = principal.getName();

		// użytkownik
		Uzytkownik uzytkownik = uzytkownikRepository.findByEmail(email).get(0);

		// jego koszyk
		Koszyk koszyk = uzytkownik.getKoszyk();

		// produkt
		Produkt produktDoZapisu = produktRepository.findById(produkt.getId()).get();

		if (produktDoZapisu.getKoszyk() == null) {
			produktDoZapisu.setKoszyk(koszyk);
			koszyk.getProdukty().add(produktDoZapisu);
			koszykRepository.save(koszyk);
		}
		return "redirect:/";
	}

	@GetMapping("/koszyk")
	public String koszyk(Model model, Principal principal) {
		String email = principal.getName();

		// użytkownik
		Uzytkownik uzytkownik = uzytkownikRepository.findByEmail(email).get(0);

		// jego koszyk
		Koszyk koszyk = uzytkownik.getKoszyk();
		
		model.addAttribute("produkty", koszyk.getProdukty());
		
		double suma = 0;
		for(Produkt produkt : koszyk.getProdukty()) {
			suma += produkt.getCena();
		}
		
		model.addAttribute("suma", suma);	
		return "koszyk";
	}
	
	
	@PostMapping("/usunZKoszyka")
	public String usunProdukt(@ModelAttribute Produkt produkt, Principal principal) {

		String email = principal.getName();

		// użytkownik
		Uzytkownik uzytkownik = uzytkownikRepository.findByEmail(email).get(0);

		// jego koszyk
		Koszyk koszyk = uzytkownik.getKoszyk();

		// produkt
		Produkt produktDoUsuniecia = produktRepository.findById(produkt.getId()).get();

		if (produktDoUsuniecia.getKoszyk() != null) {
			produktDoUsuniecia.setKoszyk(null);
			koszykRepository.save(koszyk);
		}
		return "redirect:/";
	}	
	
}