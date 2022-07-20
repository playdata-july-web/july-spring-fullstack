package kr.pe.july.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pe.july.model.dto.LikeDTO;
import kr.pe.july.model.dto.SpotDTO;
import kr.pe.july.model.entity.Like;
import kr.pe.july.model.entity.Spot;
import kr.pe.july.model.repository.LikeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	
	private final LikeRepository likeRepository;
	
	private Like dtoToEntity(LikeDTO likeDTO) {
		ObjectMapper mapper = new ObjectMapper();
		Like like = Like.builder()
						.username(likeDTO.getUsername())
						.spot(mapper.convertValue(likeDTO.getSpot(), new TypeReference<Spot>() {}))
						.build();
		return like;
	}
	
	private LikeDTO entityToDto(Like like) {
		ObjectMapper mapper = new ObjectMapper();
		LikeDTO likeDTO = LikeDTO.builder()
									.likeId(like.getLikeId())
									.username(like.getUsername())
									.spot(mapper.convertValue(like.getSpot(), new TypeReference<SpotDTO>() {}))
									.build();
		return likeDTO;
	}
	
	private List<LikeDTO> entitysToDtos(List<Like> likes) {
		return likes.stream().map(like -> entityToDto(like)).collect(Collectors.toList());
	}
	
	public List<LikeDTO> findByUser(String username) {
		return entitysToDtos(likeRepository.findByUsername(username));
	}
	
	public LikeDTO addLike(LikeDTO likeDTO) {
		return entityToDto(likeRepository.save(dtoToEntity(likeDTO)));
	}
	
	public boolean deleteLike(LikeDTO likeDTO) {
		Like like = likeRepository.findById(likeDTO.getLikeId()).orElseThrow(() -> new EntityNotFoundException("Like Not Found : " + likeDTO.getLikeId()));
		likeRepository.delete(like);
		return true;
	}
}
