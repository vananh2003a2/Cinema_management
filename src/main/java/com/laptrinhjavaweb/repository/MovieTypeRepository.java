package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.laptrinhjavaweb.entity.MovieTypeEntity;

public interface MovieTypeRepository extends JpaRepository<MovieTypeEntity, Integer>{
	@Query(value = "SELECT * FROM movietype ", nativeQuery = true)
	List<MovieTypeEntity> findAll();
	
	MovieTypeEntity findByIdMovie(int idMovieType);
	
	@Query(value = "select * from movie where idMovieType = ?",nativeQuery = true)
	MovieTypeEntity findOneById(int idMovie);
}
