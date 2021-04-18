package com.marko.MebleOnLine.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marko.MebleOnLine.data.Produkt;
import com.marko.MebleOnLine.repository.ProduktRepository;

@Controller
public class BedroomController {

	@Autowired
	ProduktRepository productRepository;
	
	@GetMapping("/bedroom")
	public String bedroom(Model model) {
		List<Produkt> produkty = productRepository.findAll();
		List<Produkt> bedroom = produkty.stream().filter((p) -> p.getKategoria().equals("bedroom") && 
								p.getKoszyk() == null).collect(Collectors.toList());
		model.addAttribute("produkty", bedroom);
		return "bedroom";
	}
}