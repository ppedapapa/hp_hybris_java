package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaklee.security.stereotypes.CurrentUser;

@Controller
public class HealthPrintResource {

	@RequestMapping("/healthprint")
	public String healthprint(@RequestParam(value="country") String country,
			@RequestParam(value="language") String language) {
		return "redirect:/?country="+country+"&language="+language;
	}
	
	
	@RequestMapping("/samlSuccess")
	public String samlSuccess(HttpSession session) {
		String country = (String) session.getAttribute("country");
		String language = (String) session.getAttribute("language");
		
		return "redirect:/?userLogged=true&country="+country+"&language="+language;
	}
	
	
	
	@RequestMapping("/healthprint-results")
	public String results(HttpSession session) {
		String country = (String) session.getAttribute("country");
        String language = (String) session.getAttribute("language");

        return "redirect:/?userLogged=true&country="+country+"&language="+language;
	}
	
	@RequestMapping("/healthprint-results/{hpid}")
	public String resultsWithId(@PathVariable(value="hpid") String hpid) {
		return "forward:/";
	}
}
