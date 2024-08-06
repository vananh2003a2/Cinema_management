package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.TransactionHistoryConverter;
import com.laptrinhjavaweb.dto.TransactionHistoryDTO;
import com.laptrinhjavaweb.entity.TransactionHistoryEntity;
import com.laptrinhjavaweb.repository.TransactionHistoryRepository;
import com.laptrinhjavaweb.service.ITransactionHistoryService;

@Service
public class TransactionHistoryService implements ITransactionHistoryService {

	@Autowired
	TransactionHistoryRepository repo;
	
	@Autowired 
	TransactionHistoryConverter converter;
	
	@Override
	public List<TransactionHistoryDTO> getTransactionHistoryById(int idUset) throws Exception {
		List<TransactionHistoryEntity> lstEntity = repo.getAllTransactionHistory(idUset);
		List<TransactionHistoryDTO> lstDTO = new ArrayList<>();
		for (TransactionHistoryEntity entity : lstEntity) {
			TransactionHistoryDTO dto = converter.toDTO(entity);
			lstDTO.add(dto);
		}
		return lstDTO;
	}

}
