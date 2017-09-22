package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Configuration
@Value
@EqualsAndHashCode(callSuper=false)
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

	UserDetailsService userDetailsService;
	AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
				.disable();

		http.authorizeRequests()
				.antMatchers("**/secured/**")
				.hasAnyRole("ADMIN")
				.anyRequest()
				.permitAll();

		http.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("/login")
				.successForwardUrl("/success")
				// .failureForwardUrl("/failure")
				.failureHandler(authenticationFailureHandler)
				.permitAll();

		http.sessionManagement()
				.sessionFixation()
				.migrateSession()
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true);

		http.logout()
				.invalidateHttpSession(true)
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(getPasswordEncoder());

		/*
		 * auth.inMemoryAuthentication() .withUser("user") .password("password")
		 * .roles("USER") .and() .withUser("admin") .password("admin")
		 * .roles("ADMIN","USER");
		 */
	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encode(rawPassword).equals(encodedPassword);
			}

		};
	}
}
