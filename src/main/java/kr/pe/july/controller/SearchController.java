package kr.pe.july.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pe.july.exception.SearchResultNotFoundException;
import kr.pe.july.model.dto.SpotDTO;
import kr.pe.july.model.service.SpotService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {
	
	private final SpotService spotService;
	
	
	
	@GetMapping("/search")
	public List<SpotDTO> search(@RequestParam("option") String option, @RequestParam("keyword") String keyword) throws SearchResultNotFoundException {
		List<SpotDTO> spots = null;

		if(option.equals("title")) {
			spots = spotService.findByTitle(keyword);
		} else if(option.equals("tag")){
			spots = spotService.findByTagContaining(keyword);
		}
		
		if(spots.isEmpty()) {
			throw new SearchResultNotFoundException("검색 결과가 없습니다.");
		}
		
		return spots;
	}
	
	@GetMapping("/external")
	public List<SpotDTO> addDatas() throws Exception {
		List<SpotDTO> spots = new ArrayList<SpotDTO>();
		
		String apiKey = "u8gf9ezmh3p8q7vx";
		String url = "http://api.visitjeju.net/vsjApi/contents/searchList?apiKey=" + apiKey + "&locale=kr&category=c1";
		
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		String result = restTemplate.getForObject(url, String.class);
		JsonNode items = mapper.readTree(result).get("items");
		
		if(items.isArray()) {
			for(JsonNode item : items) {
				String title = item.get("title").asText();
				String address = item.get("roadaddress").asText();
				String tag = item.get("tag").asText();
				String intro = item.get("introduction").asText();
				String imgPath = item.get("repPhoto").get("photoid").get("imgpath").asText();
				
				SpotDTO spot = SpotDTO.builder()
										.title(title)
										.address(address)
										.tag(tag)
										.intro(intro)
										.imgpath(imgPath)
										.build();
				
				spots.add(spotService.addSpot(spot));
			}
		}
		
		return spots;
	}
}
