package com.laptrinhjavaweb.dto;



import com.laptrinhjavaweb.entity.ChairTimeEntity;
import com.laptrinhjavaweb.entity.TicketEntity;

import lombok.Data;
@Data
public class ChairTicketDTO {
	private int idChairTicket;
	private int idChairTime;
	private int idTicket;
}
