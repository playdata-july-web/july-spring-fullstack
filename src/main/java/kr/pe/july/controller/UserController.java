package kr.pe.july.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.july.exception.DuplicateUserException;
import kr.pe.july.model.dto.UserCreateForm;
import kr.pe.july.model.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class UserController {

	private final UserService userService;
	
	@ApiOperation(value="회원가입 페이지", notes="회원가입 버튼을 클릭 시 보이는 회원가입 페이지입니다.")
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";

	}
	
	
	@ApiOperation(value = "회원가입", notes = "회원가입 시 회원 정보가 DB에 저장됩니다. \n 중복되는 아이디로 회원가입을 시도하면 중복되는 아이디라고 사용자에게 보여준 후 회원가입이 실패됩니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) throws DuplicateUserException {
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
		
		if(!userCreateForm.getUsername().equals(userService.findByUsername(userCreateForm.getUsername()).getUsername())) {
			
			userService.create(userCreateForm);
			
		}else {
			throw new DuplicateUserException("중복된 ID 입니다");
		}
		

		return "login_form";
	}

}
