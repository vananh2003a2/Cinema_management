package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.TransactionHistoryEntity;

public interface TransactionHistoryRepository  extends JpaRepository<TransactionHistoryEntity, Integer> {
	
	@Query(nativeQuery = true, value = "CALL TransactionHistory(?)")
	List<TransactionHistoryEntity> getAllTransactionHistory(int idUser);
	
}
