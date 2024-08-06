package com.laptrinhjavaweb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	UserEntity findByEmail(String email);
	//phân trang user
	@Query(value = "SELECT * FROM useraccount ORDER BY idUser LIMIT ?1 OFFSET ?2", nativeQuery = true)
	List<UserEntity> findAll(int limit, int offset);

	@Query(value = "SELECT COUNT(*) FROM useraccount", nativeQuery = true)
	long totalItems();
	
	UserEntity findByIdUser(Integer idUser);
	
	@Query("SELECT u FROM UserEntity u ORDER BY u.idUser DESC")
	 List<UserEntity> findAllOrderByIdUserDesc(); 
	
	@Query("SELECT u FROM UserEntity u WHERE u.fullName LIKE %:key%")
	List<UserEntity> findByName(@Param("key") String key);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE useraccount SET dateOfBirth = ?1, email = ?2, fullName = ?3, phone = ?4 WHERE idUser = ?5")
	void updateUser (Date dateOfBirth, String email, String fullName, String phone, int id );
}
