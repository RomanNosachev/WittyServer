package com.wittyhome.core.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler
extends SimpleUrlAuthenticationSuccessHandler
{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, authentication);
		
		HttpSession session = request.getSession();
		
		if (Objects.nonNull(session))
		{
			String username;
			String resultUsername = null;
					
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			
			if (!username.isBlank() && !username.equals("anonymousUser")) {			
				resultUsername = username;
			}
			
			session.setAttribute("username", resultUsername);
			
			Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>)    
					SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			
			List<String> roles = authorities.stream()
					.map(auth -> auth.getAuthority())
					.collect(Collectors.toList());
			
			roles.forEach(role -> {
				session.setAttribute(role.substring(5).toLowerCase(), role);
			});		
		}
	}
}
