package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.TransactionHistoryDTO;

public interface ITransactionHistoryService {
	List<TransactionHistoryDTO> getTransactionHistoryById(int idUset) throws Exception;
}
