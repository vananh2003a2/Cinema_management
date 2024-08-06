package com.laptrinhjavaweb.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TransactionHistoryEntity {
	@Id
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
