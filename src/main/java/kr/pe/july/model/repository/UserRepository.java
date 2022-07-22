package kr.pe.july.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.july.model.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUsername(String username);
	
}
