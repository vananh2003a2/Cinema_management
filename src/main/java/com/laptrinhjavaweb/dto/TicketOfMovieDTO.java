package com.laptrinhjavaweb.dto;

import java.util.Date;

import lombok.Data;
@Data
public class TicketOfMovieDTO {
	private int idMovie;
	private String movieName;
	private Date movieDate;
	private int status;
	private int total;

}