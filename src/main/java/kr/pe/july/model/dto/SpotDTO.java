package kr.pe.july.model.dto;

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
public class SpotDTO {
	
	private int id;
	
	private String tag;
	
	private String title;
	
	private String address;
	
	private String intro;
	
	private String imgpath;
}
