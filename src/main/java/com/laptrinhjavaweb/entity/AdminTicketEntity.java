package com.laptrinhjavaweb.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AdminTicketEntity {
	@Id
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
