package musicfy.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import musicfy.model.User;
import musicfy.repository.UserRepository;

@Service
public class AppUserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	
	
	public AppUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User existingUser = userRepository.findByEmail(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), getAuthorities(existingUser));
	    }
	private Collection<? extends GrantedAuthority> getAuthorities(User existingUser) {
	        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+existingUser.getRol().name()));
	}
	

}
