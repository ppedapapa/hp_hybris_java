package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shaklee.security.stereotypes.CurrentUser;

@Controller
public class ResultsResource {

	@RequestMapping("/results")
	public String results() {
		return "forward:/index";
	}
}
