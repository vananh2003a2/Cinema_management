package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.TicketDTO;
import com.laptrinhjavaweb.entity.TicketEntity;



@Component
public class TicketConverter {
	public TicketDTO toDTO (TicketEntity ticketEntity) {
		TicketDTO dto = new TicketDTO();
		dto.setId_Ticket(ticketEntity.getId_Ticket());
		dto.setBookingTime(ticketEntity.getBookingTime());
		dto.setRefund(ticketEntity.getRefund());
		dto.setStatus(ticketEntity.getStatus());
		dto.setTicketCode(ticketEntity.getTicketCode());
		dto.setTotalAmount(ticketEntity.getTotalAmount());
		dto.setIdMovie(ticketEntity.getIdMovie().getIdMovie());
		dto.setIdUser(ticketEntity.getIdUser().getIdUser());
		return dto;
	}
}
