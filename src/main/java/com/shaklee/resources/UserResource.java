package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaklee.DAO.UserDAO;
import com.shaklee.security.stereotypes.CurrentUser;
import com.shaklee.shared.util.StatusResponse;

@RestController
@RequestMapping("/services/user")
public class UserResource {
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(path = "/isUserLoggedIn", method = GET)
	public UserData getUserDetails(@CurrentUser User user)
	{	
		if (user == null)
			return new UserData("user_not_logged", null) ;
		
		return new UserData("user_logged", user.getUsername()) ;
		
	}
	
	public static class UserData extends StatusResponse
	{
		public String message;

		public String email;
		public String userId;
		public String first_name;
		public String last_name;

		public UserData(String message, String userName) {
			super(SUCCESS);
			this.message = message;

		}
		
		
		
	}
	
	

}
