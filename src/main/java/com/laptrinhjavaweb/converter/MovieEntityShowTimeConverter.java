package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.MovieTypeEntity;

@Component
public class MovieEntityShowTimeConverter {
	public MovieEntityShowTime toDTO(MovieEntity movieEntity) {
		MovieTypeEntity typeEntity = movieEntity.getIdMovieType();

		MovieEntityShowTime dto = new MovieEntityShowTime();
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
		dto.setIdMovieType(typeEntity.getIdMovieType());
		dto.setTypeName(typeEntity.getTypeName());
		
		return dto;
	}
}