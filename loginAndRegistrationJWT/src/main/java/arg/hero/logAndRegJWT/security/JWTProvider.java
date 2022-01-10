package arg.hero.logAndRegJWT.security;

import javax.annotation.PostConstruct;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTProvider {
	
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	public String generateToken(Authentication auth) {
		User principal = (User) auth.getPrincipal();
		return Jwts.builder()
				.setSubject(principal.getUsername())
				.signWith(key)
				.compact();
	}
	
	public boolean validate(String jwt) {
		Jwts.parser()
			.setSigningKey(key)
			.parseClaimsJws(jwt);
		
		return true;
	}

	public String extractUsernameFromToken(String jwt) {
		Claims claims = Jwts.parser()
							.setSigningKey(key)
							.parseClaimsJws(jwt)
							.getBody();
		
		return claims.getSubject();
	}
}
