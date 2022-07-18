package kr.pe.july.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationProviderImp implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsServiceImp service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetailsImp user = (UserDetailsImp) service.loadUserByUsername(authentication.getName());
		String reqPassword = authentication.getCredentials().toString();
		
		if(!passwordEncoder.matches(reqPassword, user.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
