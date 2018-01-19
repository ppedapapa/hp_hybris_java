package com.shaklee.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaklee.util.StatusResponse;

@RestController
@RequestMapping("/services/user")
public class UserResource {
	
	@RequestMapping(path = "/isUserLoggedIn", method = GET)
	public UserData testUserId(Principal principal)
	{
		/*String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}*/
		
		if (principal == null)
			return new UserData("user_not_logged", null) ;
		
		return new UserData("user_logged", principal.getName()) ;
		
	}
	
	public static class UserData extends StatusResponse
	{
		public String message;

		public String userName;

		public UserData(String message, String userName) {
			super(SUCCESS);
			this.message = message;
			this.userName = userName;
		}
		
		
		
	}

}
