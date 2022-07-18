package kr.pe.july.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.july.model.entity.Spot;

public interface SpotRepository extends CrudRepository<Spot, Integer> {
	
	List<Spot> findByTitle(String title);
	
	List<Spot> findByTagContaining(String tag);
}
