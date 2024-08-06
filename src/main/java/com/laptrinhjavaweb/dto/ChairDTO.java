package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.laptrinhjavaweb.entity.ChairTimeEntity;
import com.laptrinhjavaweb.entity.ChairTypeEntity;
import com.laptrinhjavaweb.entity.RoomEntity;

import lombok.Data;

@Data
public class ChairDTO {
	private int idCinemaChair;
	private String chairCode;
	private ChairTypeEntity idChairType;
	private RoomEntity idRoom;
}