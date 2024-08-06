package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.ChairTicketDTO;
import com.laptrinhjavaweb.entity.ChairTicketEntity;
@Component
public class ChairTicketConverter {
    public ChairTicketDTO toDTO(ChairTicketEntity chairticketEntity) {
        ChairTicketDTO dto = new ChairTicketDTO();
        dto.setIdChairTicket(chairticketEntity.getIdChairTicket());
        dto.setIdChairTime(chairticketEntity.getIdChairTime().getIdChairTime()); // Sử dụng getId() để lấy id của ChairTimeEntity
        dto.setIdTicket(chairticketEntity.getIdTicket().getId_Ticket()); // Sử dụng getId() để lấy id của TicketEntity
        return dto;
    }
}
