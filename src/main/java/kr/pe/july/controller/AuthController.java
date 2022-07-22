package kr.pe.july.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@ApiOperation(value = "로그인 페이지")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@GetMapping("/form")
	public String form() {
		return "login_form";
	}
	
	@ApiOperation(value = "로그인", notes = "user table에 입력하는 사용자의 정보가 있을 시 로그인을 하면 home 페이지로 이동합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@GetMapping("/login")
	public String loginSuccess() {
		return "index";
	}
	
	@ApiOperation(value = "로그아웃", notes = "로그인된 상태에서 로그아웃 버튼 클릭 시 로그아웃 완료하고 home 페이지로 이동합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@GetMapping("/logout")
	public String logout() {
		return "index";
	}
}
