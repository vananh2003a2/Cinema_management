package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.ChairTimeDTO;
import com.laptrinhjavaweb.entity.ChairTicketEntity;
import com.laptrinhjavaweb.entity.ChairTimeEntity;

@Component
public class ChairTimeConverter {
	public ChairTimeDTO toDTO(ChairTimeEntity entity) {
		ChairTimeDTO dto = new ChairTimeDTO();
		dto.setIdChairTime(entity.getIdChairTime());
		dto.setStatus(entity.getStatus());
		dto.setChair(entity.getIdChair());
		dto.setMovieTimeRoom(entity.getIdMovieTimeRoom());
		return dto;
	}
	public ChairTimeEntity toEntity(ChairTimeDTO dto) {
		ChairTimeEntity entity = new ChairTimeEntity();
		entity.setIdChairTime(dto.getIdChairTime());
		entity.setStatus(dto.getStatus());
		entity.setIdChair(dto.getChair());
		entity.setIdMovieTimeRoom(dto.getMovieTimeRoom());
		return entity;
	}
}