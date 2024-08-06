package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="cinemaRoom")
@Data
public class RoomEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRoom;
	@Column
	private String RoomCode;
	@Column
	private int chairTotal;
	@OneToMany(mappedBy = "idRoom")
	private List<ChairEntity> chairs= new ArrayList<ChairEntity>();

	@OneToMany(mappedBy = "idRoom")
	private List<MovieTimeRoomEntity> movieTimeRooms = new ArrayList<MovieTimeRoomEntity>();
}
