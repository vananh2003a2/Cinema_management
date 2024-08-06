package com.laptrinhjavaweb.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.ComboEntity;

public interface ChairRepository extends JpaRepository<ChairEntity, Integer>{
	@Query(value = "SELECT * FROM chair WHERE idRoom = ?",nativeQuery = true)
	List<ChairEntity> findChairByIdRoom(int idRoom);
	/* Lấy danh sách ghế đã được đặt của 1 suất chiếu*/
	@Query(value = "SELECT * FROM chair WHERE idCinemaChair IN (SELECT idChair FROM chairtime WHERE idMovieTimeRoom = ? AND status = 1)",nativeQuery = true)
	List<ChairEntity> getBookedChairs(int idMovieTimeRoom);
	/* Lấy danh sách ghế chưa được đặt của 1 suất chiếu*/
	@Query(value = "SELECT * FROM chair where idRoom = ? AND idCinemaChair  NOT IN (select idChair from chairtime where idMovieTimeRoom = ?)",nativeQuery = true)
	List<ChairEntity> getUnbookedChairs(int idRoom, int idMovieTimeRoom);
	@Query(value = "SELECT * FROM chair WHERE idCinemaChair = ?)",nativeQuery = true)
	List<ChairEntity> findOneById(int idCinemaChair);
	
	ChairEntity findByIdCinemaChair(Integer id);
	
	List<ChairEntity> findAll();
	@Query(value = "SELECT * FROM chair WHERE idCinemaChair = ?",nativeQuery = true)
	List<ChairEntity> findChairByIdChair(int idCinemaChair);
}