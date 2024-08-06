package com.laptrinhjavaweb.dto;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class TransactionHistoryDTO {
	private Integer id;
	private int idUser;
	private String email;
	private String fullName;
	private String ticketCode;
	private Date BookingTime;
	private float totalAmount;
	private int status;
	private Boolean refund;
	private String movieName;
	private Time beginTime;
	private String RoomCode;
	private String chairCode;
}


