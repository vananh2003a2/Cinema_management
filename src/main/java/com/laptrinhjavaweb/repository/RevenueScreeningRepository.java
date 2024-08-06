package com.laptrinhjavaweb.repository;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.RevenueScreeningEntity;

public interface RevenueScreeningRepository extends JpaRepository<RevenueScreeningEntity, Time>{
	@Query(value = "CALL tk_suatchieu()",nativeQuery = true)
	List<RevenueScreeningEntity> revenueScreening();
}