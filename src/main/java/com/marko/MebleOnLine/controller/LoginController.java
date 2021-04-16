package com.marko.MebleOnLine.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.marko.MebleOnLine.config.MyUserDetails;
import com.marko.MebleOnLine.data.Uzytkownik;
import com.marko.MebleOnLine.repository.UzytkownikRepository;

@Controller
@SessionAttributes({ "currentUser" })
public class LoginController {

	@Autowired
	UzytkownikRepository uzytkownikRepository;
	
	@Autowired
	AuthenticationManager authManager;

	@GetMapping("/logowanie")
	public String loginPage(Model model) {
		return "logowanie";
	}

	@GetMapping("/loginFailed")
	public String loginError(Model model) {
		model.addAttribute("error", "true");
		return "logowanie";
	}

	@GetMapping("/wyloguj")
	public String logout(SessionStatus session) {
		SecurityContextHolder.getContext().setAuthentication(null);
		session.setComplete();
		return "redirect:/wylogowany";
	}

	@PostMapping("/logowanie")
	public String postLogin(@ModelAttribute Uzytkownik uzytkownik, Model model, HttpSession session) {
		// read principal out of security context and set it to session
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        validatePrinciple(authentication.getPrincipal());
//        
//        Uzytkownik loggedInUser = ((MyUserDetails) authentication.getPrincipal()).getUserDetails();
//        
//        model.addAttribute("currentUser", loggedInUser.getEmail());
//        session.setAttribute("userId", loggedInUser.getId());

		
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(uzytkownik.getEmail(), uzytkownik.getHaslo());
		Authentication auth = authManager.authenticate(authReq);
		
		Uzytkownik loggedInUser = ((MyUserDetails) authReq.getPrincipal()).getUserDetails();
		model.addAttribute("currentUser", loggedInUser.getImie());
		session.setAttribute("user", loggedInUser);
		
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

		return "redirect:/";
	}

	
//	dodatek
//	
//	
//	
//	@RequestMapping(value = "/postLogin", method = RequestMethod.POST)
//    public String postLogin(Model model, HttpSession session) {
//        // read principal out of security context and set it to session
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        validatePrinciple(authentication.getPrincipal());
//        User loggedInUser = ((DefaultUserDetails) authentication.getPrincipal()).getUserDetails();
//        model.addAttribute("currentUser", loggedInUser.getEmailAdress());
//        session.setAttribute("userId", loggedInUser.getId());
//        return "redirect:/";
//    }
	
	
	private void validatePrinciple(Object principal) {
		if (!(principal instanceof MyUserDetails)) {
			throw new IllegalArgumentException("Not null!");
		}
	}

//	@PostMapping("/logowanie")
//	public String verifyLoginPage(@ModelAttribute Uzytkownik uzytkownik, Model model) {
//		model.addAttribute("uzytkownik", uzytkownik);
//		
//		List<Uzytkownik> uzytkonicy = uzytkownikRepository.findByEmail(uzytkownik.getEmail());
//		if(!uzytkonicy.isEmpty()) {			
//			Uzytkownik znalezionyUzytkownik = uzytkonicy.get(0);
//
//			if(znalezionyUzytkownik.getHaslo().equals(uzytkownik.getHaslo())) {			
//				return "adminPanel";
//			}
//		}
//		
//		return "errorLogin";
//	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/wylogowany")
	public String wylogowany() {
		return "wylogowany";
	}
	
	@GetMapping("/zalogowany")
	public String zalogowany() {
		return "zalogowany";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
	@GetMapping("/404")
	public String error404() {
		return "404";
	}
	
//	@GetMapping("/*")
//	public String handle() {
//	    return "404";
//	}

}
