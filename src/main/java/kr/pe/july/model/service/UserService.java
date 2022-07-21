package kr.pe.july.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pe.july.model.dto.UserCreateForm;
import kr.pe.july.model.entity.User;
import kr.pe.july.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User create(UserCreateForm usercreate) {
		User user = User.builder()
						.username(usercreate.getUsername())
						.password(passwordEncoder.encode(usercreate.getPassword1()))
						.role("Member").build();
		return userRepository.save(user);
	}
	
	private UserCreateForm entityToDto(User user) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(user, new TypeReference<UserCreateForm>() {});
	}
	
	public UserCreateForm findByUsername(String username) {
		return entityToDto(userRepository.findByUsername(username));
	}
}
