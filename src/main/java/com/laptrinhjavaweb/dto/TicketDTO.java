package com.laptrinhjavaweb.dto;

import java.util.Date;



import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import lombok.Data;

@Data
public class TicketDTO {
	private int id_Ticket;
	private Date BookingTime;
	private String ticketCode;
	private Float totalAmount;
	private int status;
	private Boolean refund;
	private int idUser;
	private int idMovie;
	
}

