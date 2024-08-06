package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.AdminTicketDTO;
import com.laptrinhjavaweb.entity.AdminTicketEntity;

@Component
public class AdminTicketConverter {
	public AdminTicketDTO toDTO(AdminTicketEntity entity) {
		AdminTicketDTO dto =  new AdminTicketDTO();
		dto.setId(entity.getId());
		dto.setId_Ticket(entity.getId_Ticket());
		dto.setBookingTime(entity.getBookingTime());
		dto.setRefund(entity.getRefund());
		dto.setStatus(entity.getStatus());
		dto.setTicketCode(entity.getTicketCode());
		dto.setTotalAmount(entity.getTotalAmount());
		dto.setIdUser(entity.getIdUser());
		dto.setFullName(entity.getFullName());
		dto.setIdMovie(entity.getIdMovie());
		dto.setIdMovie(entity.getIdMovie());
		dto.setMovieName(entity.getMovieName());
		dto.setChairCode(entity.getChairCode());
		dto.setBeginTime(entity.getBeginTime());
		dto.setIdCombo(entity.getIdCombo());
		dto.setComboName(entity.getComboName());
		return dto;
		
	}
}