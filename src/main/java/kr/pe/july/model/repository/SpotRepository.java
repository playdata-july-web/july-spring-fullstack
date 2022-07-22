package kr.pe.july.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.july.model.entity.Spot;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
	
	List<Spot> findByTitle(String title);
	
	List<Spot> findByTagContaining(String tag);
}
