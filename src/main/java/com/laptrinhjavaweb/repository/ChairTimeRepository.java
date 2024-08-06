package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.ChairTicketEntity;
import com.laptrinhjavaweb.entity.ChairTimeEntity;
import com.laptrinhjavaweb.entity.ComboEntity;

public interface ChairTimeRepository extends JpaRepository<ChairTimeEntity, Integer>{
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO chairtime(status, idChair, idMovietimeRoom) VALUES ( 1, ?1, ?2)")
    void addTicket(int idChair, int idMovieTiemRoom);
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM chairtime WHERE idChair = ?1 and idMovieTimeRoom = ?2")
    void deleteTicket(int idChair, int idMovieTimeRoom);
	
	@Query(value = "SELECT * FROM chairtime WHERE idChair = ? AND idMovieTimeRoom = ? AND status = 1", nativeQuery = true)
	ChairTimeEntity findOne(int idChair, int idMovieTimeRoom);
	
	List<ChairTimeEntity> findAll();
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM chairtime WHERE idChairTime = ?1")
    List<ChairTimeEntity> getChairTime(int idChairTime);
}
