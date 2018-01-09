package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shaklee.security.stereotypes.CurrentUser;

@Controller
public class LoginResource {

	@RequestMapping("/index")
	public String landing(@CurrentUser User user, Model model) {
		return "redirect:/landing";
	}
}