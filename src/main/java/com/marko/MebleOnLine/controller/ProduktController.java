package com.marko.MebleOnLine.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.marko.MebleOnLine.data.Produkt;
import com.marko.MebleOnLine.repository.ProduktRepository;

@Controller
public class ProduktController {

	@Autowired
	ProduktRepository productRepository;

	@GetMapping("/produkty")
	public String produkty(Model model) {
		List<Produkt> produkty = productRepository.findAll();
		model.addAttribute("produkty", produkty);
		return "produkty";
	}
	
	@PostMapping("/dodajProdukt")
	public String dodajProdukt(@ModelAttribute Produkt produkt) {
		productRepository.save(produkt);
		return "redirect:/produkty";
	}
	
	@GetMapping("/dodajProdukt")
	public String dodajProdukt() {
		return "dodajProdukt";
	}
	
	@PostMapping("/usunProdukt")
	public String usunProdukt(@ModelAttribute Produkt produkt) {
		productRepository.deleteById(produkt.getId());
		return "redirect:/produkty";
	}
	
	@GetMapping("/edytujProdukt/{id}")
	public String edytujProdukt(@PathVariable(name = "id") String id, Model model) {
		Optional<Produkt> produkt = productRepository.findById(Integer.parseInt(id));
		if(produkt.isPresent()) {
			model.addAttribute("produkt", produkt.get());
			return "edytujProdukt";
		}
		return "redirect:/produkty";
	}

	@PostMapping("/edytujProdukt")
	public String edytujProdukt(@ModelAttribute Produkt produkt) {
		productRepository.save(produkt);
		return "redirect:/produkty";
	}
	
	@GetMapping("/salon")
	public String salon(Model model) {
		List<Produkt> produkty = productRepository.findAll();
		List<Produkt> salon = produkty.stream().filter((p) -> p.getKategoria().equals("salon")&& p.getKoszyk() == null).collect(Collectors.toList());
		model.addAttribute("produkty", salon);
		return "salon";
	}
	
	@GetMapping("/sypialnia")
	public String sypialnia(Model model) {
		List<Produkt> produkty = productRepository.findAll();
		List<Produkt> sypialnia = produkty.stream().filter((p) -> p.getKategoria().equals("sypialnia") && p.getKoszyk() == null).collect(Collectors.toList());
		model.addAttribute("produkty", sypialnia);
		return "sypialnia";
	}
	
	@GetMapping("/pokojDzieciecy")
	public String pokojDzieciecy(Model model) {
		List<Produkt> produkty = productRepository.findAll();
		List<Produkt> pokojDzieciecy = produkty.stream().filter((p) -> p.getKategoria().equals("pokoj_dzieciecy")&& p.getKoszyk() == null).collect(Collectors.toList());
		model.addAttribute("produkty", pokojDzieciecy);
		return "pokojDzieciecy";
	}
	
}