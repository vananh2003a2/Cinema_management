package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.ChairConverter;
import com.laptrinhjavaweb.converter.ChairTypeConverter;
import com.laptrinhjavaweb.dto.ChairDTO;
import com.laptrinhjavaweb.dto.ChairTypeDTO;
import com.laptrinhjavaweb.entity.ChairEntity;
import com.laptrinhjavaweb.entity.ChairTypeEntity;
import com.laptrinhjavaweb.repository.ChairRepository;
import com.laptrinhjavaweb.repository.ChairTicketRepository;
import com.laptrinhjavaweb.repository.ChairTypeRepository;
@Service
public class ChairTypeService implements com.laptrinhjavaweb.service.ChairTypeService{
	@Autowired
	private ChairTypeRepository chairTypeRepository;

	@Autowired
	private ChairTypeConverter chairTypeConverter;
	@Override
	public List<ChairTypeDTO> getchairtypeById(int id) throws Exception {
		List<ChairTypeEntity> lst = chairTypeRepository.findChairTypeByIdChairType(id);
        List<ChairTypeDTO> lstDTO = new ArrayList<>();
        for (ChairTypeEntity chair : lst) {
        	ChairTypeDTO dto = chairTypeConverter.toDTO(chair);
            lstDTO.add(dto);
        }
        return lstDTO;
	}
	
}
