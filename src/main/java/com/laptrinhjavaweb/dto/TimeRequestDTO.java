package com.laptrinhjavaweb.dto;

import lombok.Data;

@Data
public class TimeRequestDTO {
	private String[] checkedValues;
    private String movieId;
    private String roomId;
    private String dateView;
    private String timeInput;
}