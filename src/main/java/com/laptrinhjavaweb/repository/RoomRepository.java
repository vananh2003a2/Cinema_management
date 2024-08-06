package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer>{
	RoomEntity findByidRoom(Integer idRoom);
	RoomEntity findByIdRoom(int idRoom);
}
