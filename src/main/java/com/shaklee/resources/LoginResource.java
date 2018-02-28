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
	public String login(@RequestParam(value="country" , required = false) String country,
			@RequestParam(value="lang", required = false) String lang, HttpSession session) {
		
		if (country != null && lang != null)
		{
			session.setAttribute("country", country);
			session.setAttribute("lang", lang);
		}
		return "redirect:/saml/login";
	}
	
	
}
