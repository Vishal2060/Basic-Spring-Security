package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		com.example.demo.model.User user = userRepository.findByUserName(userName);

		if (user == null)
			throw new UsernameNotFoundException("User not found");

		return User.withUsername(user.getUserName())
				.password(user.getPassword())
				.roles(user.getRoles()
						.stream()
						.map(x -> x.getRoleName())
						.toArray(String[]::new))
				.build();
	}

}
