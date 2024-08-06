package com.laptrinhjavaweb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MovieEntityShowTime implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idMovie;
	private String movieName;
	private String mainImage;
	private String thumnail;
	private Date movieDate;
	private int movieDuration;
	private String director;
	private String actors;
	private String movieScript;
	private String movieFormat;
	private int status;
	private Integer idMovieType;
	private String typeName;
	
}