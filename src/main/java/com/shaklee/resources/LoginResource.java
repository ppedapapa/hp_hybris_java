package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginResource {
	
	@RequestMapping("/healthprint-login")
	public String login(@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "lang", required = false) String lang, HttpSession session) {

		if (country != null && lang != null) {
			session.setAttribute("country", country);
			session.setAttribute("lang", lang);

		}
		return "redirect:/saml/login";
	}
	
	@RequestMapping(path="/hp-logout", method = GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		 
	    // Delete all the cookies
	    if (cookies != null) {
	 
	        for (int i = 0; i < cookies.length; i++) {
	 
	            Cookie cookie = cookies[i];
	            cookies[i].setValue(null);
	            cookies[i].setMaxAge(0);
	            response.addCookie(cookie);
	        }
	    }

	    return "redirect:/";
	}
	
	@RequestMapping(path="/samlLogout", method = GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
	  
	    return "forward:/saml/logout?local=true";
	}
	
}
