package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
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
@Table(name = "comboTicket")
@Data
public class ComboTicketEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComboTicket;
	@Column
	private int quantity;
	@Column
	private Float sumPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCombo")
	private ComboEntity idCombo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idTicket")
	private TicketEntity idTicket;
	
}
