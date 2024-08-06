package com.laptrinhjavaweb.dto;

import java.util.List;

import lombok.Data;
@Data
public class MovieTimeDetailApiResponse {
	List<MovieTimeRoomDTO> listmovietime ;
	List<String> ngaychieus ;
	Integer idMovie;
}
