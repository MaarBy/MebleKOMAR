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
public class ChildrenController {

	@Autowired
	ProduktRepository productRepository;
	
	
	@GetMapping("/children")
	public String children(Model model) {
		List<Produkt> produkty = productRepository.findAll();
		List<Produkt> children = produkty.stream().filter((p) -> p.getKategoria().equals("children") && 
				p.getKoszyk() == null).collect(Collectors.toList());
		model.addAttribute("produkty", children);
		return "children";
	}
}
