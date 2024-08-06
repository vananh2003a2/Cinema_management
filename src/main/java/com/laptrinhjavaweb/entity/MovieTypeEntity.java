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
@Table(name="movieType")
@Data
public class MovieTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovieType;
	@Column
	private String typeName;
	@OneToMany(mappedBy = "idMovieType")
	private List<MovieEntity> idMovie = new ArrayList<MovieEntity>();
}
