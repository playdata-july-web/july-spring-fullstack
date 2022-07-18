package kr.pe.july.model.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.pe.july.model.entity.User;
import kr.pe.july.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	 private final  UserRepository userRepository;
	 private final  PasswordEncoder passwordEncoder;
	 
	 public User create(String username, String password) {
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(passwordEncoder.encode(password));
	        user.setRole("Member");
	        this.userRepository.save(user);
	        return user;
	}
	
}
