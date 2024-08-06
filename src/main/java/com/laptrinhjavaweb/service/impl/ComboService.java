package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.ComboConverter;
import com.laptrinhjavaweb.dto.ComboDTO;
import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.entity.ComboEntity;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.repository.ComboRepository;
import com.laptrinhjavaweb.service.IComboService;

@Service
public class ComboService implements IComboService {
    @Autowired
    private ComboRepository repo;

    @Autowired
    private ComboConverter cv;

    @Override
    public List<ComboDTO> getcombo() throws Exception {
        List<ComboEntity> lstComboEntity = repo.findAll();
        List<ComboDTO> lstComboDTO = new ArrayList<>();
        for (ComboEntity comboEntity : lstComboEntity) {
            ComboDTO dto = cv.toDTO(comboEntity);
            lstComboDTO.add(dto);
        }
        return lstComboDTO;
    }



	@Override
	public List<ComboDTO> getCombobyid2(int id) {
		List<ComboEntity> lstComboEntity = repo.getCombo(id);
        List<ComboDTO> lstComboDTO = new ArrayList<>();
        for (ComboEntity comboEntity : lstComboEntity) {
            ComboDTO dto = cv.toDTO(comboEntity);
            lstComboDTO.add(dto);
        }
        return lstComboDTO;
	}

	@Override
	public ComboDTO getCombobyid(int id) {
		ComboDTO comboDTO =  cv.toDTO(repo.findByIdCombo(id));
		return comboDTO;
	}

	
}
