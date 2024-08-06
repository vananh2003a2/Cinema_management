package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.RevenueMovieEntity;

public interface RevenueMovieRepository extends JpaRepository<RevenueMovieEntity, Integer>{
	@Query(value = "CALL tk_all_movie();", nativeQuery = true)
	public List<RevenueMovieEntity> revenueAllMovie();
}