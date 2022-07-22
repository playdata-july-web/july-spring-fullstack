package kr.pe.july.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.july.model.dto.LikeDTO;
import kr.pe.july.model.service.LikeService;
import kr.pe.july.model.service.SpotService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {
	
	private final LikeService likeService;
	private final SpotService spotService;
	
	@ApiOperation(value = "좋아요 목록", notes = "사용자가 누른 모든 좋아요 목록을 확인합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@GetMapping
	public List<LikeDTO> getLikes() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return likeService.findByUser(auth.getName());
	}
	
	@ApiOperation(value = "좋아요", notes = "사용자가 좋아요 버튼을 클릭 시에 사용자 정보와 관광지 정보를 pick table에 저장합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@PostMapping
	public LikeDTO addLikes(@RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LikeDTO like = LikeDTO.builder()
								.username(auth.getName())
								.spot(spotService.findById(id))
								.build();
		return likeService.addLike(like);
	}
	
	@ApiOperation(value = "좋아요 취소", notes = "사용자가 좋아요 버튼을 한 번 더 클릭 시에 pick table에 있는 정보를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
	@DeleteMapping
	public boolean deleteLikes(@RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LikeDTO like = LikeDTO.builder()
								.likeId(id)
								.username(auth.getName())
								.spot(spotService.findById(id))
								.build();
		return likeService.deleteLike(like);
	}
}
