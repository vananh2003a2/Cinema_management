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
@Table(name="Chair")
@Data
public class ChairEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCinemaChair;
	@Column
	private String chairCode;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idChairType")
	private ChairTypeEntity idChairType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idRoom")
	private RoomEntity idRoom;
	
	@OneToMany(mappedBy = "idChair")
	private List<ChairTimeEntity> chairTimes = new ArrayList<ChairTimeEntity>();
	
	
	
}
