package arg.hero.logAndRegJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.logAndRegJWT.dto.LoginRequest;
import arg.hero.logAndRegJWT.dto.RegisterRequest;
import arg.hero.logAndRegJWT.model.AppUser;
import arg.hero.logAndRegJWT.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@PostMapping("/signup")
	public ResponseEntity<AppUser> signUp(@RequestBody RegisterRequest registerRequest) {
		return new ResponseEntity<AppUser>(service.register(registerRequest), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
		return service.login(loginRequest);
	}
	
	@GetMapping("/authenticate")
	public String authenticate() {
		return "Token Works";
	}
}
