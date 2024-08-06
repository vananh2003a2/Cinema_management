package com.laptrinhjavaweb.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.ComboTicketEntity;

public interface ComboRepository extends JpaRepository<ComboEntity, Integer> {
	List<ComboEntity> findAll();
	
	ComboEntity findByIdCombo(Integer id);
	
	@Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM combo WHERE idCombo = ?1")
    List<ComboEntity> getCombo(int idCombo);
}