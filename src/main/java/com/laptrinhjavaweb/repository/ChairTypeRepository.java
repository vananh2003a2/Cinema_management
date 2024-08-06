package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.ChairTypeEntity;

public interface ChairTypeRepository  extends JpaRepository<ChairTypeEntity, Integer>{
	@Transactional
    @Modifying
	@Query(value = "SELECT * FROM chairtype WHERE idChairType = ?",nativeQuery = true)
	List<ChairTypeEntity> findChairTypeByIdChairType(int idChairType);
}
