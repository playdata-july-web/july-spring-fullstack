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

@ApiModel(value="관광지 정보", description = "관광지 번호, tag, title, address, intro, imgpath 정보를 보유한 Domain class")
public class SpotDTO {
	
	@ApiModelProperty(example="1")
	private int id;
	
	@ApiModelProperty(example="템플스테이,실내,휴식/힐링,커플,흐림,사계절,문화관광,체험,어트랙션")
	private String tag;
	
	@ApiModelProperty(example="백제사 템플스테이")
	private String title;

	@ApiModelProperty(example="제주시 애월읍 광령남6길 54 백제사")
	private String address;
	
	@ApiModelProperty(example="백제사에서의 템플 스테이 프로그램을 통하여 자신을 돌아볼 수 있는 시간이 된다.")
	private String intro;
	
	@ApiModelProperty(example="https://api.cdn.visitjeju.net/photomng/imgpath/202110/27/322a97f2-9f86-400e-93ab-3068b0faf636.jpg")
	private String imgpath;
}
