package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.WebUser;
import com.example.lottery.service.JwtTokenProvider;

@RestController
@RequestScope
@RequestMapping("login")
@CrossOrigin
public class LoginController {
	@Autowired
	private AuthenticationManager authMngr;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping
	public String signin(@RequestBody @Validated WebUser webUser) {
		System.err.println(webUser);
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					webUser.username(), webUser.password());
			authMngr.authenticate(authToken);
			return jwtTokenProvider.createToken(webUser.username());
		} catch (Exception e) {
			System.err.println("Log in failed for user " + webUser.username());
		}
		return "error";
	}

}