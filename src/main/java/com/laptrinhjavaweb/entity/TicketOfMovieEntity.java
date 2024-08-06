package com.laptrinhjavaweb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TicketOfMovieEntity {
	@Id
	private int idMovie;
	private String movieName;
	private Date movieDate;
	private int status;
	private int total;

}