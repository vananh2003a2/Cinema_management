package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;
@Component
public class ComboTicketConverter {
	public ComboTicketDTO toDTO (ComboTicketEntity comboticketEntity) {
		ComboTicketDTO dto = new ComboTicketDTO();
		dto.setIdCombo(comboticketEntity.getIdCombo().getIdCombo());
		dto.setQuantity(comboticketEntity.getQuantity());
		dto.setSumPrice(comboticketEntity.getSumPrice());
		dto.setIdCombo(comboticketEntity.getIdCombo().getIdCombo());
		dto.setIdTicket(comboticketEntity.getIdTicket().getId_Ticket());
		return dto;
	}
}
