package kr.pe.july.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@ApiModel(value="좋아요 정보", description = "좋아요 번호, 사용자 아이디, 관광지 정보를 보유한 Domain class")
public class LikeDTO {
	
	@ApiModelProperty(example="1")
	private int likeId;
	
	@ApiModelProperty(example="test@test.com")
	private String username;
	
	@ApiModelProperty(example="spot")
	private SpotDTO spot;
}
