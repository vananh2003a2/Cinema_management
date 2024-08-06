package com.laptrinhjavaweb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.DailyRevenueEntity;

public interface DailyRevenueRepository extends JpaRepository<DailyRevenueEntity, Integer>{
	@Query(value = "CALL CalculateDailyTotalAmount(?,?)",nativeQuery = true)
	List<DailyRevenueEntity> findAllByDate(Date fromDate, Date toDate);
}