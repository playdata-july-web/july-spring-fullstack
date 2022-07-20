package kr.pe.july.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.july.model.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {

	public List<Like> findByUsername(String username);
}
