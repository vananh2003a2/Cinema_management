package com.laptrinhjavaweb.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
	@Autowired 
	private RoleRepository roleRepo;
	
	@Override
	public String ecrypt(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String enrtext;
		MessageDigest msd = MessageDigest.getInstance("MD5");
		byte[] srctextbyte = text.getBytes("UTF-8");
		byte[] enrtextbyte = msd.digest(srctextbyte);
		BigInteger big = new BigInteger(1, enrtextbyte);
		enrtext = big.toString(16);
		return enrtext;
	}

	@Override
	public UserEntity ktDangKi(String emailInput) throws Exception {
		UserEntity userEntity = null;
		userEntity = userRepository.findByEmail(emailInput);
		return userEntity;
	}

	@Override
	public void dangKi(UserDTO userDTO) throws Exception {
		if (userDTO.getDateOfBirth()!=null) {
			java.util.Date utilDate = userDTO.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			userDTO.setDateOfBirth(sqlDate);
		}
		userDTO.setPassword(ecrypt(userDTO.getPassword()));
		UserEntity userEntity = userConverter.toEntity(userDTO);
		userEntity.setStatus(1);
		userRepository.save(userEntity);
	}


	public List<UserEntity> findAll(int page, int size) {
        int offset = page * size;
        return userRepository.findAll(size, offset);
    }

    public long getTotalItems() {
        return userRepository.totalItems();
    }

	@Override
	public UserDTO getUserById(int id) {
		UserDTO userDTO = new UserDTO();
		userDTO=userConverter.toDTO(userRepository.findByIdUser(id));
		return userDTO;
	}

	@Override
	public UserDTO findOneById(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllUser() throws Exception {
		List<UserEntity> entity = userRepository.findAllOrderByIdUserDesc();
		List<UserDTO> lstUserDTO = new ArrayList<>();
		for (UserEntity userEntity : entity) {
			UserDTO dto = new UserDTO();
			dto = userConverter.toDTO(userEntity);
			lstUserDTO.add(dto);
		}
		return lstUserDTO;
	}

	@Override
	public List<UserDTO> getUserByName(String search) throws Exception {
		List<UserEntity> entity = userRepository.findByName(search);
		List<UserDTO> lstUserDTO = new ArrayList<>();
		for (UserEntity userEntity : entity) {
			UserDTO dto = userConverter.toDTO(userEntity);
			lstUserDTO.add(dto);
		}
		return lstUserDTO;
	}

	@Override
	public void updateUser(Date dateOfBirth, String email, String fullName, String phone, int id) throws Exception {
		userRepository.updateUser(dateOfBirth, email, fullName, phone, id);	
		
	}

	@Override
	public UserDTO findByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		return userConverter.toDTO(userEntity);
	}

	@Override
	public UserDTO changePassword(int idUser, String password) throws Exception {
		UserDTO userDTO = userConverter.toDTO(userRepository.findOne(idUser)); 
		userDTO.setPassword(ecrypt(password));
		UserEntity userEntity = userConverter.toUserEntity(userDTO);
		RoleEntity role = roleRepo.findOne(2);
		List<RoleEntity> lst_role = new ArrayList<RoleEntity>();
		lst_role.add(role);
		userEntity.setRoles(lst_role);
		userRepository.save(userEntity);
		return userDTO;
	}

	@Override
	public UserDTO LockAccount(int idUser) {
		UserEntity enitity = userRepository.findOne(idUser);
		enitity.setStatus(0);
		UserDTO dto =  userConverter.toDTO(userRepository.save(enitity));
		return dto;
	}

	@Override
	public UserDTO UnlockAccount(int idUser) {
		UserEntity enitity = userRepository.findOne(idUser);
		enitity.setStatus(1);
		UserDTO dto =  userConverter.toDTO(userRepository.save(enitity));
		return dto;
	}

}