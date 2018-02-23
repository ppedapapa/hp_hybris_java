package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shaklee.security.stereotypes.CurrentUser;

@Controller
public class HealthPrintResource {

	@RequestMapping("/healthprint")
	public String healthprint() {
		return "forward:/";
	}
	
	@RequestMapping("/{country}/{language}")
	public String indexWithCountry(@PathVariable(value="country") String country,
			@PathVariable(value="language") String language) {
		return "redirect:/?country="+country+"&language="+language;
	}
	@RequestMapping("/samlSuccess")
	public String samlSuccess(HttpSession session) {
		String country = (String) session.getAttribute("country");
		String language = (String) session.getAttribute("language");
		
		return "redirect:/?userLogged=true&country="+country+"&language="+language;
	}
	
	@RequestMapping("/healthprint/{country}/{language}")
	public String healthprintByCountry(@PathVariable(value="country") String country,
			@PathVariable(value="language") String language) {
		return "forward:/?country="+country+"&language="+language;
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
