package kr.pe.july.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString

@Entity
public class Spot {
	
	@Id
	@Column(name = "spot_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String tag;
	
	private String title;
	
	private String address;
	
	private String intro;
	
	private String imgpath;
}
