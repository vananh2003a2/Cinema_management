package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
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

import lombok.Data;

@Entity
@Table(name="chairTime")
@Data
public class ChairTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChairTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idChair")
	private ChairEntity idChair;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idMovieTimeRoom")
	private MovieTimeRoomEntity idMovieTimeRoom;
	
	@Column
	private int status;
	
	@OneToMany(mappedBy = "idChairTime")
	private List<ChairTicketEntity> chairTickets = new ArrayList<ChairTicketEntity>();	
	
	
	
}
