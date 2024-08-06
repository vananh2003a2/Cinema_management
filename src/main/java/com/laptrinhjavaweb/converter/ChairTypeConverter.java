package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ChairTypeDTO;
import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.ChairTypeEntity;
@Component
public class ChairTypeConverter {
	public ChairTypeDTO toDTO(ChairTypeEntity entity) {
		ChairTypeDTO dto = new ChairTypeDTO();
		dto.setIdChairType(entity.getIdChairType());
		dto.setChairTypeName(entity.getChairTypeName());
		dto.setPrice(entity.getPrice());
		
		return dto;
	}
}
