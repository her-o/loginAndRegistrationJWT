package arg.hero.logAndRegJWT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import arg.hero.logAndRegJWT.dto.LoginRequest;
import arg.hero.logAndRegJWT.dto.RegisterRequest;
import arg.hero.logAndRegJWT.model.AppUser;
import arg.hero.logAndRegJWT.repository.UserRepository;
import arg.hero.logAndRegJWT.security.JWTProvider;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTProvider jwtProvider;
	
	public AppUser register(RegisterRequest registerRequest) {
		AppUser user = new AppUser();
		user.setEmail(registerRequest.getEmail());
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		return userRepository.save(user);
	}

	public String login(LoginRequest loginRequest) {
		
		Authentication authentication =	authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
																	  loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
		
	}
	

}
