package com.laptrinhjavaweb.dto;

import java.sql.Time;

import lombok.Data;
@Data
public class TimeRange {
	private Time timeBegin;
	private Time timeEnd;

}