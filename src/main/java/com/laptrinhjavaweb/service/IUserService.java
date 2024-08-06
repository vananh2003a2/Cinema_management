package com.laptrinhjavaweb.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public interface IUserService {
	String ecrypt(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException;

	UserEntity ktDangKi(String emailInput) throws Exception;

	void dangKi(UserDTO userDTO) throws Exception;

	// phân trang user
	List<UserEntity> findAll(int page, int size);

	long getTotalItems();

	UserDTO findOneById(int idUser);

	List<UserDTO> getAllUser() throws Exception;

	List<UserDTO> getUserByName(String search) throws Exception;

	UserDTO getUserById(int id);

	void updateUser(Date dateOfBirth, String email, String fullName, String phone, int id) throws Exception;

	UserDTO findByEmail(String email);

	UserDTO changePassword(int idUser, String password) throws Exception;

	// Khóa tài khoản
	UserDTO LockAccount(int idUser);

	// Mở khóa tài khoản
	UserDTO UnlockAccount(int idUser);
}