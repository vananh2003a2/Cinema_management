package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.laptrinhjavaweb.entity.TicketOfMovieEntity;

public interface TicketOfMovieRepository extends JpaRepository<TicketOfMovieEntity, Integer>{
	@Query(value = "CALL ticket_of_movie()",nativeQuery = true)
	List<TicketOfMovieEntity> showTicketOfMovie();
}