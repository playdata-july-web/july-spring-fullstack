package kr.pe.july.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//회원가입을 위한 폼 클래스
//@ApiModel(value="회원가입을 위한 DTO", description = "username, password의 정보를 보유한 Domain class")
//@Schem(description = "게시물 리스트 응답DTO")
public class UserCreateForm {
	
	//길이가 3-25 사이여야 한다는 검증조건 
   // @Size(min = 3, max = 25)
	@Email //이메일 형식으로만 가입해야함
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;
    
    //비밀번호 한 번 더 확인
    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;
    
}