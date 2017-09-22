package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping("/success")
	public ResponseEntity<String> success() {
		return new ResponseEntity<String>("Login Success", HttpStatus.OK);
	}

	@PostMapping("/failure/{error}")
	public ResponseEntity<String> failure(@PathVariable("error") String error) {
		return new ResponseEntity<String>(error, HttpStatus.UNAUTHORIZED);
	}
}
