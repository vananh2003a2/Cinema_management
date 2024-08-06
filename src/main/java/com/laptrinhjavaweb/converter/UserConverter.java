package com.laptrinhjavaweb.converter;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConverter {
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setAddress(entity.getAddress());
		dto.setIdUser(entity.getIdUser());
		dto.setDateOfBirth(entity.getDateOfBirth());
		dto.setEmail(entity.getEmail());
		dto.setFullName(entity.getFullName());
		dto.setPhone(entity.getPhone());
		dto.setPassword(entity.getPassword());
		dto.setStatus(entity.getStatus());
		dto.setResetPasswordToken(entity.getResetPasswordToken());
		return dto;
	}
	public UserEntity toAdminEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setIdUser(dto.getIdUser());
		entity.setAddress(dto.getAddress());
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setEmail(dto.getEmail());
		entity.setFullName(dto.getFullName());
		entity.setPhone(dto.getPhone());
		entity.setPassword(dto.getPassword());
		entity.setStatus(dto.getStatus());
		entity.setResetPasswordToken(dto.getResetPasswordToken());
		return entity;
	}
	public UserEntity toUserEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setIdUser(dto.getIdUser());
		entity.setAddress(dto.getAddress());
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setEmail(dto.getEmail());
		entity.setFullName(dto.getFullName());
		entity.setPhone(dto.getPhone());
		entity.setPassword(dto.getPassword());
		entity.setStatus(dto.getStatus());
		entity.setResetPasswordToken(dto.getResetPasswordToken());
		return entity;
	}
	public UserEntity toEntity(UserDTO userDTO) {
		UserEntity entity = new UserEntity();
		entity.setEmail(userDTO.getEmail());
		entity.setFullName(userDTO.getFullName());
		entity.setAddress(userDTO.getAddress());
		entity.setDateOfBirth(userDTO.getDateOfBirth());
		entity.setPhone(userDTO.getPhone());
		entity.setStatus(userDTO.getStatus());
		entity.setPassword(userDTO.getPassword());
		return entity;
	}
}