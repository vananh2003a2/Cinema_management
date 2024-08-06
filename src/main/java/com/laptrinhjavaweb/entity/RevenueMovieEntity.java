package com.laptrinhjavaweb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class RevenueMovieEntity {
	@Id
	private int idMovie;
	private String movieName;
	private Float total;
}