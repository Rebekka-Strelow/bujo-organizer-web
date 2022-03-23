package bujo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import bujo.web.service.UserDetailsService;

@Controller
public class MainController {
	//Dieser Controller verteilt Aufrufe von Seiten an die entsprechenden JSPs

	@Autowired
	UserDetailsService service;
	
	@GetMapping("/")
	public String showHome() {
		System.out.println(service.getAll());
		
		return "home";
	}
	
	@GetMapping("/showLogin")
	public String showLogin() {
		return "custom-login";
	}
	
	
}
