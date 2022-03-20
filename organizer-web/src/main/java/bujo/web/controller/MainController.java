package bujo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import bujo.web.data.dao.AccountDAO;
import bujo.web.service.AccountService;

@Controller
public class MainController {
	//Dieser Controller verteilt Aufrufe von Seiten an die entsprechenden JSPs

	@Autowired
	AccountService account_service;
	
	@GetMapping("/")
	public String showHome() {
		System.out.println(account_service.getAllAccounts());
		
		
		return "home";
	}
	
	@GetMapping("/showLogin")
	public String showLogin() {
		return "custom-login";
	}
	
	
}
