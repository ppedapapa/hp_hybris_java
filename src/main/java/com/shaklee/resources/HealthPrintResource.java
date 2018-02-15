package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
	
	@RequestMapping("/healthprint/{country}/{language}")
	public String healthprintByCountry(@PathVariable(value="country") String country,
			@PathVariable(value="language") String language) {
		return "forward:/?country="+country+"&language="+"language";
	}
	
	@RequestMapping("/healthprint-results")
	public String results() {
		return "forward:/";
	}
	
	@RequestMapping("/healthprint-results/{hpid}")
	public String resultsWithId(@PathVariable(value="hpid") String hpid) {
		return "forward:/";
	}
}
