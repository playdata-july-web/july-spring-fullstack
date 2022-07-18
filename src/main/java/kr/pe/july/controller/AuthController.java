package kr.pe.july.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@GetMapping("/admin")
	public String admin() {
		return "ADMIN 인증 성공";
	}
	
	@GetMapping("/member")
	public String member() {
		return "MEMBER 인증 성공";
	}
	
	@PostMapping("/fail")
	public String fail() {
		return "로그인 실패";
	}
	
	@GetMapping("/home")
	public String home() {
		return "로그아웃 성공";
	}
}
