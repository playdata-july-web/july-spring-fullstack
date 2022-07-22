package kr.pe.july.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

@Entity(name = "pick")
public class Like {
	
	@Id
	@Column(name = "pick_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;
	
	@Column(name = "user_name")
	private String username;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spot_id")
	private Spot spot;
}
