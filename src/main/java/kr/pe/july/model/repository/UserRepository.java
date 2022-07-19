package kr.pe.july.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import kr.pe.july.model.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
