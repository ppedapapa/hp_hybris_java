package com.shaklee.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginResource {
	
	@RequestMapping("/healthprint-login")
	public String login(@RequestParam(value="country") String country,
			@RequestParam(value="language") String language, HttpSession session) {
		  
		session.setAttribute("country", country);
		session.setAttribute("language", language);
		
		return "redirect:/saml/login";
	}
}
