package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.dto.ComboTicketDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;

public interface ComboTicketRepository extends JpaRepository<ComboTicketEntity, Integer>{
	
	List<ComboTicketEntity> findAll();
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM comboticket WHERE idTicket = ?1")
    List<ComboTicketEntity> getComboTicket(int idTicket);
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM comboticket WHERE idTicket = ?1")
    void deleteTicket(int idTicket);

	
}
