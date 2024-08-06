package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.AdminTicketEntity;

public interface AdminTicketRepository  extends JpaRepository<AdminTicketEntity, Integer> {
	
	@Query(nativeQuery = true, value = "CALL adminTicket()")
	List<AdminTicketEntity> getAllAdminTicket();
	
	@Query(nativeQuery = true, value = "CALL searchTicketByKey(?)")
	List<AdminTicketEntity> getTicketByKey(String key);
	
	@Query(nativeQuery = true, value = "CALL searchTicketByStatus(?)")
	List<AdminTicketEntity> getTicketByStatus(int status);
	
	@Query(nativeQuery = true, value = "CALL searchTicketByKeyAndStatus( ?, ?)")
	List<AdminTicketEntity> getTicketByKeyAndStatus(int status, String key);
	
}
