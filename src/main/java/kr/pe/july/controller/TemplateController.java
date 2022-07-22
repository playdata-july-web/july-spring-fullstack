package kr.pe.july.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/api/home")
public class TemplateController {
	
	@ApiOperation(value = "메인페이지", notes = "시작 시에 보이는 페이지입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@GetMapping
	public String home() {
		
		return "index";
	}
}
