package com.laptrinhjavaweb.dto;


import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.TicketEntity;

import lombok.Data;

@Data
public class ComboTicketDTO {
	private int idComboTicket;
	private int quantity;
	private Float sumPrice;
	private int idCombo;
	private int idTicket;
}
