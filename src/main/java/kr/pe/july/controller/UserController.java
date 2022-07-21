package kr.pe.july.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.july.model.dto.UserCreateForm;
import kr.pe.july.model.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";

	}

	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		// 에러를 담아내는 BindingResult
		// 에러가 있을시 폼으로 넘어감
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}

		// pw1과 pw2가 일치하지 않으면 폼으로 넘어감
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			// bindingResult.rejectValue(필드명, 오류코드, 에러메시지)를 의미
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		
		if(!userCreateForm.getUsername().equals(userService.findByUsername(userCreateForm.getUsername()))) {
			
			userService.create(userCreateForm);
			
		}else {
			
		}
		

		return "login_form";
	}

}
