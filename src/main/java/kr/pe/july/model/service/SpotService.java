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
	
	private List<SpotDTO> entitysToDtos(List<Spot> spots) {
		ObjectMapper mapper = new ObjectMapper();
		return spots.stream().map(spot -> mapper.convertValue(spot, new TypeReference<SpotDTO>() {})).collect(Collectors.toList());
	}
	
	private SpotDTO entityToDto(Spot spot) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(spot, new TypeReference<SpotDTO>() {});
	}
	
	private Spot dtoToEntity(SpotDTO spotDTO) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(spotDTO, new TypeReference<Spot>() {});
	}
	
	public List<SpotDTO> findByTitle(String title) {
		return entitysToDtos(spotRepository.findByTitle(title));
	}
	
	public List<SpotDTO> findByTagContaining(String tag) {
		return entitysToDtos(spotRepository.findByTagContaining(tag));
	}
	
	public SpotDTO addSpot(SpotDTO spotDTO) {
		return entityToDto(spotRepository.save(dtoToEntity(spotDTO)));
	}
}
