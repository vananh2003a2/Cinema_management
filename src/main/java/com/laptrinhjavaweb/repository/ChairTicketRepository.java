package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.ChairTicketEntity;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;

public interface ChairTicketRepository extends JpaRepository<ChairTicketEntity,Integer>{
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO chairticket(idChairTime, idTicket) VALUES (?1, ?2)")
    void addTicket(int idChairTime, int idTicket);
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM chairticket WHERE idTicket = ?1")
    void deleteTicket( int idTicket);
	
	List<ChairTicketEntity> findAll();
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM chairticket WHERE idTicket = ?1")
    List<ChairTicketEntity> getChairTicket(int idTicket);
}
