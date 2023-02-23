package com.squad3.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	@Email
	@NotBlank
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",message = "Min 1 uppercase letter.\r\n Min 1 lowercase letter.\r\n Min 1 special character.\r\n Min 1 number.\r\n Min 8 characters.")
	private String password;
}
