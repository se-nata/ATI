package ru.se_nata.ati.controller;



  import java.security.Principal;
  
  import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import
  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import
  org.springframework.web.servlet.ModelAndView;

import ru.se_nata.ati.security.UserDetailsServiceImpl;
  
  @Controller
  public class LoginController {
	
	    @GetMapping("/login")
	    public String login(Model model, String error, String logout) {
	       

	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "login";
	    }
}