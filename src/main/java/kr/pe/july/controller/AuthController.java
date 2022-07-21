package kr.pe.july.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@GetMapping("/form")
	public String form() {
		return "login_form";
	}
	
	@GetMapping("/login")
	public String loginSuccess() {
		return "login_form";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "login_form";
	}
}
