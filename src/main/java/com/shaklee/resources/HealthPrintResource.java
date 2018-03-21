package com.shaklee.resources;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaklee.DAOImpl.UserDAOImpl;
import com.shaklee.entity.HPEntity;
import com.shaklee.security.stereotypes.CurrentUser;

@Controller
public class HealthPrintResource {
	
	@Autowired
	UserDAOImpl userDao;

	@RequestMapping("/healthprint")
	public String healthprint(@RequestParam(value="country", required = false) String country,
			@RequestParam(value="lang", required = false) String lang) {
		
		if (country != null && lang != null)
			return "redirect:/?country="+country+"&lang="+lang;
		else
			return "redirect:/";
	}
	
	
	@RequestMapping("/samlSuccess")
	public String samlSuccess(HttpSession session, HttpServletResponse response, @CurrentUser User user) {
		String country = (String) session.getAttribute("country");
		String lang = (String) session.getAttribute("lang");
		
		HPEntity hpEntity = new HPEntity();
		
		hpEntity.setCountry(country);
		hpEntity.setLang( lang);
		hpEntity.setUserLogged(true);
		
		com.shaklee.entity.User userBean = userDao.findByEmail(user.getUsername());
		
		hpEntity.setFirstName(userBean.getFirstName());
		
		Cookie hpEntityCookie = new Cookie("hpEntity", hpEntity.toString());
		Cookie testCookie = new Cookie("test", "test");
		
		response.addCookie(hpEntityCookie);
		response.addCookie(testCookie);
		
		return "redirect:/";

	}
	
	
	
	@RequestMapping("/healthprint-results")
	public String results(HttpSession session) {
		String country = (String) session.getAttribute("country");
        String language = (String) session.getAttribute("language");

        return "redirect:/?country="+country+"&language="+language;
	}
	
	@RequestMapping("/healthprint-results/{hpid}")
	public String resultsWithId(@PathVariable(value="hpid") String hpid) {
		return "forward:/";
	}
}
