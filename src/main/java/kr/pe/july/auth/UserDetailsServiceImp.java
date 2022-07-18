package kr.pe.july.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pe.july.model.entity.User;
import kr.pe.july.model.repository.UserRepository;

public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	private UserDetailsImp entityToDto(User user) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(user, new TypeReference<UserDetailsImp>() {});
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return entityToDto(repository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found : " + username)));
	}
	
}
