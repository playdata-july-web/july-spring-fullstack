package kr.pe.july.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

@Entity
public class User {
	
	@Id
	@Column(name="user_name")
	private String username;
	
	@Column(name="pw")
	private String password;
	
	private String role;
}
