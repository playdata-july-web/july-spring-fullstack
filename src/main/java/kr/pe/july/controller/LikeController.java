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
	
	@GetMapping
	public List<LikeDTO> getLikes() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return likeService.findByUser(auth.getName());
	}
	
	@PostMapping
	public LikeDTO addLikes(@RequestParam("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LikeDTO like = LikeDTO.builder()
								.username(auth.getName())
								.spot(spotService.findById(id))
								.build();
		return likeService.addLike(like);
	}
	
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
