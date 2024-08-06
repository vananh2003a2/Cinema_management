package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.ChairTypeDTO;

public interface ChairTypeService {
	List<ChairTypeDTO> getchairtypeById(int id) throws Exception;
}
