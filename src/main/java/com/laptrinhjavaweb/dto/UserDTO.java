package com.laptrinhjavaweb.dto;

import java.util.Date;

import lombok.Data;
@Data 
public class UserDTO {
	private int idUser;
	
	private String email;
	
	private String fullName;

	private String address;

	private Date dateOfBirth;

	private String phone;

	private String password;

	private String resetPasswordToken;
	
	private int status;
	
}