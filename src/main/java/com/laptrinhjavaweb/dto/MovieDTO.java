package com.laptrinhjavaweb.dto;

import java.util.Date;

import com.laptrinhjavaweb.entity.MovieTypeEntity;

import lombok.Data;

@Data
public class MovieDTO {
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
	
	private MovieTypeEntity idMovieType;

	
	
}