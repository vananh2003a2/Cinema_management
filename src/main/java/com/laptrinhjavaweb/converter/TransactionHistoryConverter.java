package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.TransactionHistoryDTO;
import com.laptrinhjavaweb.entity.TransactionHistoryEntity;

@Component
public class TransactionHistoryConverter {
	public TransactionHistoryDTO toDTO(TransactionHistoryEntity entity) {
		TransactionHistoryDTO dto =  new TransactionHistoryDTO();
		dto.setId(entity.getId());
		dto.setIdUser(entity.getIdUser());
		dto.setEmail(entity.getEmail());
		dto.setFullName(entity.getFullName());
		dto.setTicketCode(entity.getTicketCode());
		dto.setBookingTime(entity.getBookingTime());
		dto.setTotalAmount(entity.getTotalAmount());
		dto.setStatus(entity.getStatus());
		dto.setRefund(entity.getRefund());
		dto.setMovieName(entity.getMovieName());
		dto.setBeginTime(entity.getBeginTime());
		dto.setRoomCode(entity.getRoomCode());
		dto.setChairCode(entity.getChairCode());
		return dto;
		
	}
}
