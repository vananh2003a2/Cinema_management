package com.laptrinhjavaweb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class DailyRevenueEntity {
	@Id
	private Date date;
	private Float total;
}