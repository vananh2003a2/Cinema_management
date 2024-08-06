package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.AdminTicketConverter;
import com.laptrinhjavaweb.dto.AdminTicketDTO;
import com.laptrinhjavaweb.entity.AdminTicketEntity;
import com.laptrinhjavaweb.repository.AdminTicketRepository;
import com.laptrinhjavaweb.service.IAdminTicketService;

@Service
public class AdminTicketService implements IAdminTicketService {

	@Autowired
	private AdminTicketRepository adminticketrepo;
	
	@Autowired
	private AdminTicketConverter adminticketconverter;
	
	@Override
	public List<AdminTicketDTO> getAllAdminTicket() throws Exception {
		List<AdminTicketEntity> listEntity = adminticketrepo.getAllAdminTicket();
		List<AdminTicketDTO> listDTO = new ArrayList<AdminTicketDTO>();
		for (AdminTicketEntity entity : listEntity) {
			AdminTicketDTO dto = new AdminTicketDTO();
			dto = adminticketconverter.toDTO(entity);
			listDTO.add(dto);
		}
		return listDTO;
	}

	@Override
	public List<AdminTicketDTO> getTicketByKey(String key) throws Exception {
		List<AdminTicketEntity> entity = adminticketrepo.getTicketByKey(key);
		List<AdminTicketDTO> lstDTO = new ArrayList<>();
		for (AdminTicketEntity adminticketEntity : entity) {
			AdminTicketDTO dto = adminticketconverter.toDTO(adminticketEntity);
			lstDTO.add(dto);
		}
		return lstDTO;
	}

	@Override
	public List<AdminTicketDTO> getTicketByStatus(int status) throws Exception {
		List<AdminTicketEntity> entity = adminticketrepo.getTicketByStatus(status);
		List<AdminTicketDTO> lstDTO = new ArrayList<>();
		for (AdminTicketEntity adminticketEntity : entity) {
			AdminTicketDTO dto = adminticketconverter.toDTO(adminticketEntity);
			lstDTO.add(dto);
		}
		return lstDTO;
	}

	@Override
	public List<AdminTicketDTO> getTicketByKeyAndStatus(int status, String key) throws Exception {
		List<AdminTicketEntity> entity = adminticketrepo.getTicketByKeyAndStatus(status, key);
		List<AdminTicketDTO> lstDTO = new ArrayList<>();
		for (AdminTicketEntity adminticketEntity : entity) {
			AdminTicketDTO dto = adminticketconverter.toDTO(adminticketEntity);
			lstDTO.add(dto);
		}
		return lstDTO;
	}
}
