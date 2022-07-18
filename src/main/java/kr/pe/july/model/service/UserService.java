package kr.pe.july.model.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.pe.july.model.dto.UserCreateForm;
import kr.pe.july.model.entity.User;
import kr.pe.july.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	 private final  UserRepository userRepository;
	 private final  PasswordEncoder passwordEncoder;
	 
	 public User create(UserCreateForm usercreate) {
	        User user = new User();
	        user.setUsername(usercreate.getUsername());
	        user.setPassword(passwordEncoder.encode(usercreate.getPassword1()));
	        user.setRole("Member");
	        this.userRepository.save(user);
	        return user;
	}
	
}
