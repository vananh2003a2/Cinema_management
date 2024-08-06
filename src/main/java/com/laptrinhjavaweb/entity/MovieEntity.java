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

import lombok.Data;

@Entity
@Table(name = "movie")
@Data
public class MovieEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovie;
	@Column
	private String movieName;
	@Column
	private String mainImage;
	@Column
	private String thumnail;
	@Column
	private Date movieDate;
	@Column
	private int movieDuration;
	@Column
	private String director;
	@Column
	private String actors;
	@Column(columnDefinition = "TEXT")
	private String movieScript;
	@Column
	private String movieFormat;
	@Column
	private int status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMovieType")
	private MovieTypeEntity idMovieType;//ten FK

	@OneToMany(mappedBy = "idMovie")
	private List<MovieTimeRoomEntity> movieTimeRooms= new ArrayList<MovieTimeRoomEntity>();
	
	@OneToMany(mappedBy = "idMovie")
	private List<TicketEntity> tickets = new ArrayList<TicketEntity>();
	
}
