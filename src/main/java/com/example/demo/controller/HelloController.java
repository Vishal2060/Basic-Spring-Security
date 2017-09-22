package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloController {

	@GetMapping("/all")
	public String hello() {
		return "hello all";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/secured/all")
	public String helloSecured() {
		return "hello secured";
	}

	@GetMapping("/getUser")
	public String getUserName() {
		// Object principal =
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// String name="";
		// if (principal instanceof UserDetails)
		// name = ((UserDetails)principal).getUsername();

		return "hello ";
	}
}
