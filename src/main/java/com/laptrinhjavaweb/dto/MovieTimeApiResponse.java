package com.laptrinhjavaweb.dto;

import java.util.List;

import lombok.Data;
@Data
public class MovieTimeApiResponse {
	private List<MovieTimeRoomDTO> listmovie;
	private List<MovieTimeRoomDTO> listmovietime;
	private List<String> ngaychieuOptions;
	private String selectedDate;
}
