package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.laptrinhjavaweb.entity.ChairEntity;

import lombok.Data;

@Data
public class ChairTypeDTO {

	private int idChairType;
	private String chairTypeName;
	private float price;
}
