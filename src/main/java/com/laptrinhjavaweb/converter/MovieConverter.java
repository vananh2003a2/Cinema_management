package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.entity.MovieEntity;

@Component
public class MovieConverter {
	public MovieDTO toDTO (MovieEntity movieEntity) {
		MovieDTO dto = new MovieDTO();
		dto.setIdMovie(movieEntity.getIdMovie());
		dto.setMovieName(movieEntity.getMovieName());
		dto.setMainImage(movieEntity.getMainImage());
		dto.setThumnail(movieEntity.getThumnail());
		dto.setMovieDate(movieEntity.getMovieDate());
		dto.setMovieDuration(movieEntity.getMovieDuration());
		dto.setDirector(movieEntity.getDirector());
		dto.setActors(movieEntity.getActors());
		dto.setMovieScript(movieEntity.getMovieScript());
		dto.setMovieFormat(movieEntity.getMovieFormat());
		dto.setStatus(movieEntity.getStatus());
//		dto.setIdMovieType(movieEntity.getIdMovieType().getIdMovieType());
//		dto.setTypeName(movieEntity.getIdMovieType().getTypeName());
		dto.setIdMovieType(movieEntity.getIdMovieType());
		dto.getIdMovieType().getTypeName();
		return dto;
	}
	
	public MovieEntity toEntity (MovieDTO movieDto) {
		MovieEntity entity = new MovieEntity();
		
		entity.setIdMovie(movieDto.getIdMovie());
		entity.setMovieName(movieDto.getMovieName());
		entity.setMainImage(movieDto.getMainImage());
		entity.setThumnail(movieDto.getThumnail());
		entity.setMovieDate(movieDto.getMovieDate());
		entity.setMovieDuration(movieDto.getMovieDuration());
		entity.setDirector(movieDto.getDirector());
		entity.setActors(movieDto.getActors());
		entity.setMovieScript(movieDto.getMovieScript());
		entity.setMovieFormat(movieDto.getMovieFormat());
		entity.setStatus(movieDto.getStatus());
		entity.setIdMovieType(movieDto.getIdMovieType());

		return entity;
	}
}
