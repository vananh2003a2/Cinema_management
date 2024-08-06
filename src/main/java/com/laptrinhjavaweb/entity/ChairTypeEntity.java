package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="chairType")
@Data
public class ChairTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChairType;
	@Column
	private String chairTypeName;
	@Column
	private Float price;
	@OneToMany(mappedBy = "idChairType")
	private List<ChairEntity> chairs=new ArrayList<ChairEntity>();
}
