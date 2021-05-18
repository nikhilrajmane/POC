package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignUpDto extends BaseDto {

	private Long id;
	private String first_name;
	private String last_name;
	private String email;
	private String mobile;
	private String password;
	private Long country_code;

}
