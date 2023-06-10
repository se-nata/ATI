package ru.se_nata.ati.security;

import java.io.IOException;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Securityhandler implements AuthenticationSuccessHandler{
	
	
	   @Override
	   public void onAuthenticationSuccess(HttpServletRequest request,   HttpServletResponse response, Authentication authentication) throws IOException  {
	        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	        if (roles.contains("ROLE_ADMIN")) {
	            response.sendRedirect("/regulatoryform/");
	        }
	        else {
				response.sendRedirect("/regulatoryact/");
			}
	    }
}
