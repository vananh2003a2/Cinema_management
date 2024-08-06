package com.laptrinhjavaweb.entity;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class RevenueScreeningEntity {
	@Id
	private Time beginTime;
	private int soVe;
}