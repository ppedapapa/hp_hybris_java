package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
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
	public String healthprint(@RequestParam(value="country", required = false) String country,
			@RequestParam(value="lang", required = false) String lang) {
		
		if (country != null && lang != null)
			return "redirect:/?country="+country+"&lang="+lang;
		else
			return "redirect:/";
	}
	
	
	@RequestMapping("/samlSuccess")
	public String samlSuccess(HttpSession session, HttpServletResponse response) {
		String country = (String) session.getAttribute("country");
		String lang = (String) session.getAttribute("lang");
		
		Cookie isUserLoggedIn = new Cookie("userLogged", "true");
		
		response.addCookie(isUserLoggedIn);
		
		if (country != null && lang != null)
			return "redirect:/?country="+country+"&lang="+lang;
		else
			return "redirect:/";

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
