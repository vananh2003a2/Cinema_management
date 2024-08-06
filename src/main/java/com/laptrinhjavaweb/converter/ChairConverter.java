package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.entity.ChairEntity;

@Component
public class ChairConverter {
	public ChairDTO toDTO(ChairEntity entity) {
		ChairDTO dto = new ChairDTO();
		dto.setChairCode(entity.getChairCode());
		dto.setIdCinemaChair(entity.getIdCinemaChair());
		dto.setIdChairType(entity.getIdChairType());
		dto.setIdRoom(entity.getIdRoom());
		return dto;
	}
}