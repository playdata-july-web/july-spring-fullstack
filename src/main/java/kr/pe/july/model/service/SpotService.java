package kr.pe.july.model.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pe.july.model.dto.SpotDTO;
import kr.pe.july.model.entity.Spot;
import kr.pe.july.model.repository.SpotRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpotService {
	
	private final SpotRepository spotRepository;
	
	public List<SpotDTO> entitysToDtos(Iterable<Spot> spots) {
		ObjectMapper mapper = new ObjectMapper();
		return StreamSupport.stream(spots.spliterator(), false).collect(Collectors.toList())
				.stream().map(spot -> mapper.convertValue(spot, new TypeReference<SpotDTO>() {})).collect(Collectors.toList());
	}
	
	public List<SpotDTO> findByTitle(String title) {
		return entitysToDtos(spotRepository.findByTitle(title));
	}
	
	public List<SpotDTO> findByTagContaining(String tag) {
		return entitysToDtos(spotRepository.findByTagContaining(tag));
	}
}
