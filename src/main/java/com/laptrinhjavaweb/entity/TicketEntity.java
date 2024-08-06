package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "ticket")
@Data
public class TicketEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Ticket;
	@CreationTimestamp
	private Date BookingTime;
	@Column
	private String ticketCode;
	@Column
	private Float totalAmount;
	@Column
	private int status;
	@Column
	private Boolean refund;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idUser")
	private UserEntity idUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idMovie")
	private MovieEntity idMovie;
	
	@OneToMany(mappedBy = "idTicket")
	private List<ChairTicketEntity> chairTickets= new ArrayList<ChairTicketEntity>();

	@OneToMany(mappedBy = "idTicket")
	private List<ComboTicketEntity> comboTickets = new ArrayList<ComboTicketEntity>(); 
}
