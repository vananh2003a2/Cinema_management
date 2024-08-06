package com.laptrinhjavaweb.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="chairTicket")
@Data
public class ChairTicketEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChairTicket;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idChairTime")
	private ChairTimeEntity idChairTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idTicket")
	private TicketEntity idTicket;
}
