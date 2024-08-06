package com.laptrinhjavaweb.entity;

import java.sql.Time;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "movieTimeRoom")
@Data
public class MovieTimeRoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovieTimeRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMovie")
	private MovieEntity idMovie;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRoom")
	private RoomEntity idRoom;

	@Column
	private Time beginTime;

	@Column
	private Time endTime;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private int status;

	@OneToMany(mappedBy = "idMovieTimeRoom")
	private List<ChairTimeEntity> chairTime = new ArrayList<ChairTimeEntity>();
}
