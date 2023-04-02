package app.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.auth.User;
import app.core.exception.CouponsSystemException;
import app.core.loginManager.LoginManager;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private LoginManager loginManager;

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		try {
			return this.loginManager.login(user);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
}
