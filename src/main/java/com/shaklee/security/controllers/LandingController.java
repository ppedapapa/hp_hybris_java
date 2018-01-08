/*
 * Copyright 2017 Vincenzo De Notaris
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.shaklee.security.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shaklee.security.stereotypes.CurrentUser;

@Controller
public class LandingController {

	@RequestMapping("/landing")
	public String landing(@CurrentUser User user, Model model, HttpServletResponse response) {
		model.addAttribute("username", 	user.getUsername());
		Cookie cookie = new Cookie("username", user.getUsername());
        cookie.setPath("/");//<-- important
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        
		return "landing";
	}

}
