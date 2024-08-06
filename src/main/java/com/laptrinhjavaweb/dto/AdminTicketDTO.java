package com.laptrinhjavaweb.dto;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class AdminTicketDTO {
	private Integer id;
	private int id_Ticket;
	private Date BookingTime;
	private Boolean refund;
	private int status;
	private String ticketCode;
	private float totalAmount;
	private int idUser;
	private String fullName;
	private int idMovie;
	private String movieName;
	private String chairCode;
	private Time beginTime;
	private Integer idCombo;
	private String comboName;
}
