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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.july.exception.SearchResultNotFoundException;
import kr.pe.july.model.dto.SpotDTO;
import kr.pe.july.model.service.SpotService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {
	
	private final SpotService spotService;
	
	
	 @ApiOperation(value = "검색 결과", notes = "tag와 title을 선택하여 해당하는 관광지 데이터를 검색할 수 있습니다. \n spot table에 없는 데이터를 검색 시에 검색 결과가 없다는 메시지를 보여줄 수 있게 예외 처리 핸들링을 합니다.")
	    @ApiResponses({
	            @ApiResponse(code = 200, message = "OK !!"),
	            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
	            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
	    })
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
	
	 @ApiOperation(value = "DB에 외부 API 저장", notes = "외부 관광지 API를 이용하여 필요한 데이터만 불러와 spot table에 저장합니다.")
	    @ApiResponses({
	            @ApiResponse(code = 200, message = "OK !!"),
	            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
	            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
	    }) 
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
