package com.example.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (exception.getClass()
				.isAssignableFrom(UsernameNotFoundException.class)) {
			request.getRequestDispatcher("/failure/UserNotFound")
					.forward(request, response);
		} else if (exception.getClass()
				.isAssignableFrom(DisabledException.class)) {
			request.getRequestDispatcher("/failure/Disabled")
					.forward(request, response);
		} else if (exception.getClass()
				.isAssignableFrom(BadCredentialsException.class)) {
			request.getRequestDispatcher("/failure/BadCredentials")
					.forward(request, response);
		} else if(exception.getClass().isAssignableFrom(SessionAuthenticationException.class)){
			request.getRequestDispatcher("/failure/session")
			.forward(request, response);
		}
	}
}
