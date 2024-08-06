package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
@Component
public class ComboConverter {
	public ComboDTO toDTO (ComboEntity comboentity) {
		ComboDTO dto = new ComboDTO();
		dto.setIdcombo(comboentity.getIdCombo());
		dto.setComboname(comboentity.getComboName());
		dto.setComboprice(comboentity.getComboPrice());
		return dto;
	}
}
