package kr.pe.july.model.repository;

import org.springframework.data.repository.CrudRepository;

import kr.pe.july.model.entity.User;

public interface UserRepository extends CrudRepository<User, String>{

}
