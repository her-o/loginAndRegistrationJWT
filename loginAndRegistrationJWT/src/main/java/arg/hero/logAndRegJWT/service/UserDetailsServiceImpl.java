package arg.hero.logAndRegJWT.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import arg.hero.logAndRegJWT.model.AppUser;
import arg.hero.logAndRegJWT.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser appUser = repository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("No user found - " + username));
		return new User(appUser.getUsername(), 
						appUser.getPassword(), 
						true, 
						true, 
						true,
						true,
						getAuthorities("ROLE_USER"));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

}
