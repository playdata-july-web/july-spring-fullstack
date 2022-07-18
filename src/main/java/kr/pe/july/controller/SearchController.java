package kr.pe.july.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.july.model.dto.SpotDTO;
import kr.pe.july.model.service.SpotService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {
	
	private final SpotService spotService;
	
	@GetMapping("/spots/{id}")
	public List<SpotDTO> search(@PathVariable("id") boolean id, @RequestParam("keyword") String keyword) {
		List<SpotDTO> spots = null;
		if(id) {
			spots = spotService.findByTitle(keyword);
		} else {
			spots = spotService.findByTagContaining(keyword);
		}
		return spots;
	}
}
