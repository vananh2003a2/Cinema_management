package com.laptrinhjavaweb.service.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.MovieTimeRoomConverter;
import com.laptrinhjavaweb.dto.MovieTimeRoomDTO;
import com.laptrinhjavaweb.dto.MovieTimeRoomOfChairDTO;
import com.laptrinhjavaweb.dto.TimeRange;
import com.laptrinhjavaweb.entity.MovieEntity;
import com.laptrinhjavaweb.entity.MovieTimeRoomEntity;
import com.laptrinhjavaweb.entity.RoomEntity;
import com.laptrinhjavaweb.repository.MovieRepository;
import com.laptrinhjavaweb.repository.MovieTimeRoomRepository;
import com.laptrinhjavaweb.repository.RoomRepository;
import com.laptrinhjavaweb.service.IMovieTimeRoomService;

@Service
public class MovieTimeRoomService implements IMovieTimeRoomService {

	@Autowired
	private MovieTimeRoomRepository movieTimeRoomRepository;

	@Autowired
	MovieTimeRoomConverter conver;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public MovieTimeRoomDTO getTimeRoom(int id) {
		MovieTimeRoomDTO movieTimeRoomDTO = new MovieTimeRoomDTO();
		movieTimeRoomDTO = conver.toDTO(movieTimeRoomRepository.findByIdMovieTimeRoom(id));
		return movieTimeRoomDTO;
	}

	@Override
	public List<Date> getDates(int idMovie) throws Exception {
		return movieTimeRoomRepository.getDates(idMovie);
	}

	@Override
	public List<MovieTimeRoomDTO> getMovieTimeRoomByIdMovieAndDate(int idMovie, Date date) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.getMovieTimeRoomByIdMovieAndDate(idMovie,
				date);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public List<MovieTimeRoomDTO> getMovieTimeRoomByIdMovieAndCurrentDate(int idMovie) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository
				.getMovieTimeRoomByIdMovieAndCurrentDate(idMovie);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public List<MovieTimeRoomDTO> finaAll() throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.findAll();
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public List<MovieTimeRoomDTO> findAllPageble(int offset, int limit) throws Exception {
		List<MovieTimeRoomEntity> lst_entity = movieTimeRoomRepository.findAllPageble(offset, limit);
		List<MovieTimeRoomDTO> lst_dto = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_entity) {
			lst_dto.add(conver.toDTO(entity));
		}
		return lst_dto;
	}

	@Override
	public int count() throws Exception {
		long count = movieTimeRoomRepository.count();
		return (int) count;
	}

	@Override
	public List<MovieTimeRoomDTO> getNewMTR(int offset, int limit) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.getNewMTR(offset, limit);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countNewMTR() throws Exception {
		int count = movieTimeRoomRepository.countNewMTR();
		return count;
	}

	@Override
	public List<MovieTimeRoomDTO> findMTRByStartName(String movieName, int limit, int offset) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.findMTRByStartName(movieName, limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public List<MovieTimeRoomDTO> searchByMovieName(String movieName, int limit, int offset) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchByMovieName(movieName, limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countsearchByMovieName(String movieName) throws Exception {
		long count = movieTimeRoomRepository.countsearchByMovieName(movieName);
		return (int) count;
	}

	@Override
	public List<MovieTimeRoomDTO> searchByIdRoom(int idRoom, int limit, int offset) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchByIdRoom(idRoom, limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countsearchByIdRoom(int IdRoom) throws Exception {
		long count = movieTimeRoomRepository.countsearchByIdRoom(IdRoom);
		return (int) count;
	}

	@Override
	public List<MovieTimeRoomDTO> searchByDate(Date date, int limit, int offset) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchByDate(date, limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countSearchByDate(Date date) throws Exception {
		long count = movieTimeRoomRepository.countSearchByDate(date);
		return (int) count;
	}

	@Override
	public List<MovieTimeRoomDTO> searchBy_IdRoom_Date(int idRoom, Date date, int limit, int offset) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchBy_IdRoom_Date(idRoom, date, limit,
				offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countSearchBy_IdRoom_Date(int idRoom, Date date) throws Exception {
		long count = movieTimeRoomRepository.countSearchBy_IdRoom_Date(idRoom, date);
		return (int) count;
	}

	@Override
	public List<MovieTimeRoomDTO> searchBy_movieName_IdRoom(String movieName, int idRoom, int limit, int offset)
			throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchBy_movieName_IdRoom(movieName, idRoom,
				limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countSearchBy_movieName_IdRoom(String movieName, int idRoom) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MovieTimeRoomDTO> searchBy_movieName_Date(String movieName, Date date, int limit, int offset)
			throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchBy_movieName_Date(movieName, date,
				limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countSearchBy_movieName_Date(String movieName, Date date) throws Exception {
		long count = movieTimeRoomRepository.countSearchBy_movieName_Date(movieName, date);
		return (int) count;
	}

	@Override
	public List<MovieTimeRoomDTO> searchBy_MovieName_Room_Date(String movieName, int idRoom, Date date, int limit,
			int offset) throws Exception {
		List<MovieTimeRoomEntity> lst_mtrEntity = movieTimeRoomRepository.searchBy_MovieName_Room_Date(movieName,
				idRoom, date, limit, offset);
		List<MovieTimeRoomDTO> lst_mtrDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity entity : lst_mtrEntity) {
			lst_mtrDTO.add(conver.toDTO(entity));
		}
		return lst_mtrDTO;
	}

	@Override
	public int countSearchBy_MovieName_Room_Date(String movieName, int idRoom, Date date) throws Exception {
		long count = movieTimeRoomRepository.countSearchBy_MovieName_Room_Date(movieName, idRoom, date);
		return (int) count;
	}

	@Override
	@Scheduled(fixedDelay = 10000)
	public void updateStatusForMovieTimeRooms() throws Exception {
		List<MovieTimeRoomEntity> lst = movieTimeRoomRepository.findAll();
		// Lấy ngày và giờ hiện tại
		Date now = new Date();
		// Lấy thời gian hiện tại
		long currentTimeMillis = System.currentTimeMillis();
		// Chuyển đổi thời gian hiện tại thành đối tượng java.sql.Time
		Time currentTime = new Time(currentTimeMillis);
		// Kiểm tra ngày hiện tại

		for (MovieTimeRoomEntity movieTimeRoomEntity : lst) {
			boolean isToday = isSameDay(movieTimeRoomEntity.getDate(), now);
			if (isToday && movieTimeRoomEntity.getBeginTime().toLocalTime().isBefore(currentTime.toLocalTime())) {
				movieTimeRoomEntity.setStatus(0);
				movieTimeRoomRepository.save(movieTimeRoomEntity);
			}
			else {
				movieTimeRoomEntity.setStatus(1);
				movieTimeRoomRepository.save(movieTimeRoomEntity);
			}
		}
	}

	// de do
	@Override
	public List<MovieTimeRoomDTO> getAllGroupByIdMovie(Date date) throws Exception {
		List<MovieTimeRoomEntity> entity = movieTimeRoomRepository.findAllByDateGroupByIdMovie(date);
		List<MovieTimeRoomDTO> lstMovieDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity movieEntity : entity) {
			MovieTimeRoomDTO dto = new MovieTimeRoomDTO();
			dto = conver.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}
		return lstMovieDTO;
	}

	@Override
	public List<MovieTimeRoomDTO> getAllGroupByIdMovie1() throws Exception {
		List<MovieTimeRoomEntity> entity = movieTimeRoomRepository.findAllGroupByIdMovie();
		List<MovieTimeRoomDTO> lstMovieDTO = new ArrayList<MovieTimeRoomDTO>();
		for (MovieTimeRoomEntity movieEntity : entity) {
			MovieTimeRoomDTO dto = new MovieTimeRoomDTO();
			dto = conver.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}
		return lstMovieDTO;
	}

	@Override
	public List<MovieTimeRoomDTO> getAllMovie() throws Exception {
		List<MovieTimeRoomEntity> entity = movieTimeRoomRepository.findAllByOrderByBeginTimeAsc();
		List<MovieTimeRoomDTO> lstMovieDTO = new ArrayList<>();
		for (MovieTimeRoomEntity movieEntity : entity) {
			MovieTimeRoomDTO dto = new MovieTimeRoomDTO();
			dto = conver.toDTO(movieEntity);
			lstMovieDTO.add(dto);
		}

		return lstMovieDTO;
	}

	@Override
	public MovieTimeRoomOfChairDTO findOneByIdMovieTimeRoom(int idMovieTimeRoom) throws Exception {
		MovieTimeRoomOfChairDTO dto = conver.toDTO2(movieTimeRoomRepository.findOneByIdMovieTimeRoom(idMovieTimeRoom));
		return dto;
	}

	// Hàm lấy ra các suất chiếu theo ngày, phòng, được sắp xếp theo thời gian bắt
	// đầu
	@Override
	public List<MovieTimeRoomOfChairDTO> findByDateAndRoomAndStatusOrderByBeginTimeAsc(Date date, RoomEntity idRoom)
			throws Exception {
		List<MovieTimeRoomOfChairDTO> lstMovieTimeDTO = new ArrayList<MovieTimeRoomOfChairDTO>();
		List<MovieTimeRoomEntity> lstMovieTimeEntity = movieTimeRoomRepository
				.findByDateAndRoomAndStatusOrderByBeginTimeAsc(date, idRoom);
		for (MovieTimeRoomEntity movieTimeRoomEntity : lstMovieTimeEntity) {
			MovieTimeRoomOfChairDTO dto = new MovieTimeRoomOfChairDTO();
			dto = conver.toDTO3(movieTimeRoomEntity);
			lstMovieTimeDTO.add(dto);
		}
		return lstMovieTimeDTO;
	}

	public Time addDuration(Time time, int duration) {
		long millis = time.getTime() + duration * 60000; // Đổi từ phút sang mili giây
		return new Time(millis);
	}

	public Time addDuration2(Time time, int duration) {
		long millis = time.getTime() - duration * 60000; // Đổi từ phút sang mili giây
		return new Time(millis);
	}

	//có sửa
		@Override
		public List<MovieTimeRoomOfChairDTO> listTime(Date date, int idRoom, int idMovie, String timeInput) throws Exception {
			// Xử lý idRoom và idMovie
			// Lấy ngày và giờ hiện tại
			System.out.println(timeInput);
			Date now = new Date();
			boolean isToday = isSameDay(date, now);
			// Lấy thời gian hiện tại
			long currentTimeMillis = System.currentTimeMillis();
			// Chuyển đổi thời gian hiện tại thành đối tượng java.sql.Time
			Time currentTime = new Time(currentTimeMillis);
			//Xử lý timeInput
			// Thêm giây vào chuỗi thời gian
	        if (timeInput.length() == 5) {
	            timeInput += ":00";
	        }

			Time time = Time.valueOf(timeInput);
			System.out.println(time);
			MovieEntity movie = movieRepository.findByIdMovie(idMovie);
			RoomEntity room = roomRepository.findByIdRoom(idRoom);

			// Danh sách suất chiếu đã tồn tại theo phòng và ngày đó
			List<MovieTimeRoomOfChairDTO> lstMovieTimeDTO = findByDateAndRoomAndStatusOrderByBeginTimeAsc(date, room);
			// List để lưu trữ các suất chiếu mới được thêm vào
			List<MovieTimeRoomOfChairDTO> newShowTimes = new ArrayList<>();
			// Thời gian bắt đầu và kết thúc của rạp chiếu phim
//			Time temp_begin = Time.valueOf("07:00:00");
			Time temp_begin = time;
//			Time temp_end = Time.valueOf("07:00:00");
			Time temp_end=time;

			// Giả sử thời gian kết thúc của rạp là 00:00:00
			Time endOfTheDay = Time.valueOf("23:59:59");

			// Xử lý nếu lstMovieTimeDTO rỗng
			if (lstMovieTimeDTO.size() == 0) {
				while (temp_end.before(endOfTheDay) || temp_end.equals(endOfTheDay)) {
					// System.out.println(temp_end + "-" + endOfTheDay);
					temp_end = addDuration(temp_end, movie.getMovieDuration() + 10); // Thời lượng phim + 10 phút nghỉ
					// Xử lý trường hợp phần tử cuối
					if (temp_end.before(endOfTheDay)) {
						// Thêm suất chiếu mới vào danh sách
						MovieTimeRoomOfChairDTO newShowTime = new MovieTimeRoomOfChairDTO();
						newShowTime.setBeginTime(temp_begin);
						newShowTime.setEndTime(temp_end);
						newShowTime.setDate(date);
						newShowTime.setIdMovie(movie);
						newShowTime.setIdRoom(room);
						newShowTime.setStatus(1);
						newShowTimes.add(newShowTime);
						temp_begin = temp_end;
					}
				}
			} else {

				while (temp_end.before(endOfTheDay) || temp_end.equals(endOfTheDay)) {

					temp_end = addDuration(temp_end, movie.getMovieDuration() + 10); // Thời lượng phim + 10 phút nghỉ
					boolean check = false;
					if (temp_end.before(lstMovieTimeDTO.get(0).getBeginTime())) {
						check = true;

					} else {
						for (int i = 0; i < lstMovieTimeDTO.size() - 1; i++) {
							if (temp_begin.after(lstMovieTimeDTO.get(i).getEndTime())
									&& temp_end.before(lstMovieTimeDTO.get(i + 1).getBeginTime())) {
								check = true;

								break;
							}
						}
					}
					if (temp_begin.after(lstMovieTimeDTO.get(lstMovieTimeDTO.size() - 1).getEndTime())
							&& temp_end.before(endOfTheDay)) {

						check = true;
					}
					if (check) {
						// Thêm suất chiếu mới vào danh sách
						MovieTimeRoomOfChairDTO newShowTime = new MovieTimeRoomOfChairDTO();
						newShowTime.setBeginTime(temp_begin);
						newShowTime.setEndTime(temp_end);
						newShowTime.setDate(date);
						newShowTime.setIdMovie(movie);
						newShowTime.setIdRoom(room);
						newShowTime.setStatus(1);
						newShowTimes.add(newShowTime);
					}
					temp_begin = temp_end;
				}
			}
			
			if (isToday) {
				List<MovieTimeRoomOfChairDTO> temp = new ArrayList<>();
				for (MovieTimeRoomOfChairDTO item : newShowTimes) {
					if (!item.getBeginTime().toLocalTime().isBefore(currentTime.toLocalTime())) {
						temp.add(item);
					}
				}
				newShowTimes = temp;
			}
//			// test
//			System.out.println(now);
//			for (MovieTimeRoomOfChairDTO movieTimeRoomOfChairDTO : newShowTimes) {
//				System.out.println(movieTimeRoomOfChairDTO.getBeginTime().toString()
//						+ movieTimeRoomOfChairDTO.getEndTime().toString());
//			}
			return newShowTimes;
		}
	@Override
	public void saveTime(List<MovieTimeRoomOfChairDTO> lstTime, String[] checkedValues) throws Exception {
		for (MovieTimeRoomOfChairDTO dto : lstTime) {
			for (String timeBegin : checkedValues) {
				if (dto.getBeginTime().toString().substring(0, 5).equals(timeBegin)) {
					System.out.println(timeBegin + "-" + dto.getBeginTime().toString().substring(0, 5));
					MovieTimeRoomEntity entity = conver.toEntity(dto);
					movieTimeRoomRepository.save(entity);
					break;
				}
			}
		}
	}

	@Override
	public List<MovieTimeRoomOfChairDTO> listConvertTime(List<MovieTimeRoomOfChairDTO> lstTime,
			MovieTimeRoomOfChairDTO movieTimeRoom) throws Exception {
		List<MovieTimeRoomOfChairDTO> listTimeNews = new ArrayList<MovieTimeRoomOfChairDTO>();
		for (MovieTimeRoomOfChairDTO movieTimeRoomOfChairDTO : lstTime) {
			if (movieTimeRoomOfChairDTO.getIdMovieTimeRoom() != movieTimeRoom.getIdMovieTimeRoom()) {
				listTimeNews.add(movieTimeRoomOfChairDTO);
			}
		}
		return listTimeNews;
	}

	@Override
	public MovieTimeRoomOfChairDTO findByIdMovieTimeRoom(int idMovieTimeRoom) throws Exception {
		MovieTimeRoomEntity entity = movieTimeRoomRepository.findOneByIdMovieTimeRoom(idMovieTimeRoom);
		MovieTimeRoomOfChairDTO dto = conver.toDTO3(entity);
		return dto;
	}

	// Kiểm tra xem 2 ngày có phải cùng một ngày không
	public boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public List<TimeRange> findTimeBegin(List<MovieTimeRoomOfChairDTO> lstTime, MovieTimeRoomOfChairDTO movieTimeRoom)// xử
																														// lý
																														// lại
																														// những
																														// suất
																														// nào
																														// của
																														// ngày
																														// hiện
																														// tại
																														// mà
																														// quá
																														// giờ
																														// hiện
																														// tại
																														// cần
																														// add
																														// thì
																														// remove
																														// khỏi
																														// mảng
			throws Exception {
		List<TimeRange> lstTimeRange = new ArrayList<TimeRange>();
		Time temp_begin = Time.valueOf("07:00:00");// thời gian bắt đâu của rạp
		Time temp_end = Time.valueOf("23:59:59");// thời gian kết thúc của rạp
		// Lấy ngày và giờ hiện tại
		Date now = new Date();
		// Lấy thời gian hiện tại
		long currentTimeMillis = System.currentTimeMillis();
		// Chuyển đổi thời gian hiện tại thành đối tượng java.sql.Time
		Time currentTime = new Time(currentTimeMillis);
		// Kiểm tra ngày hiện tại
		boolean isToday = isSameDay(movieTimeRoom.getDate(), now);
		if (lstTime.size() == 0) {// Xử lý trường hợp nếu phòng, ngày đó không có suất nào ngoài suất cần cập nhật
									// => có nghĩa ds lstTime (sau khi loại trừ ra suất cần cập nhật thì có
									// size()=0)
			TimeRange timeRange = new TimeRange();
			timeRange.setTimeBegin(temp_begin);
			timeRange.setTimeEnd(addDuration2(temp_end, movieTimeRoom.getIdMovie().getMovieDuration() + 10));
			// Neu la ngay hien tai
			boolean check = true;
			if (isToday) {
				// chuyển về localtime để so sánh mới đúng
				if (currentTime.toLocalTime().isAfter(timeRange.getTimeBegin().toLocalTime())
						&& currentTime.toLocalTime().isBefore(timeRange.getTimeEnd().toLocalTime())) {
					timeRange.setTimeBegin(currentTime);// TH curentTime nằm giữa
				}
				if (currentTime.toLocalTime().isAfter(timeRange.getTimeEnd().toLocalTime())) {
					check = false;// TH currentTime nằm sau thời gian kết thúc
				}

			}
			if (check)
				lstTimeRange.add(timeRange);
			return lstTimeRange;
		}
		// Tìm thời gian trống từ giờ mở rạp đến suất đầu tiên trong mảng
		long tmpBegin = (lstTime.get(0).getBeginTime().getTime() - temp_begin.getTime()) / 60000
				- movieTimeRoom.getIdMovie().getMovieDuration() - 10;
		if (tmpBegin > 0) {
			TimeRange timeRange = new TimeRange();
			timeRange.setTimeBegin(temp_begin);
			timeRange.setTimeEnd(
					addDuration2(lstTime.get(0).getBeginTime(), movieTimeRoom.getIdMovie().getMovieDuration() + 10));
			boolean check = true;
			// Neu la ngay hien tai
			if (isToday) {
				// Kiểm tra nếu giờ hiện tại nằm trong khoảng thời gian
				if (currentTime.toLocalTime().isAfter(timeRange.getTimeBegin().toLocalTime())
						&& currentTime.toLocalTime().isBefore(timeRange.getTimeEnd().toLocalTime())) {
					// Bắt đầu = giờ hiện tại
					timeRange.setTimeBegin(currentTime);
				}
				if (currentTime.toLocalTime().isAfter(timeRange.getTimeEnd().toLocalTime())) {
					check = false;// TH currentTime nằm sau thời gian kết thúc
				}
			}
			if (check)
				lstTimeRange.add(timeRange);
		}
		// Tìm thời gian trống khoảng giữa 2 suất
		for (int i = 0; i < lstTime.size() - 1; i++) {

			long tmp = (lstTime.get(i + 1).getBeginTime().getTime() - lstTime.get(i).getEndTime().getTime()) / 60000
					- movieTimeRoom.getIdMovie().getMovieDuration() - 10;
			System.out.println(tmp);
			if (tmp > 0) {
				TimeRange timeRange = new TimeRange();
//				System.out.println("End i: " + lstTime.get(i).getEndTime() + "_" + "Begin i+1: "
//						+ lstTime.get(i + 1).getBeginTime());
				timeRange.setTimeBegin(lstTime.get(i).getEndTime());
				timeRange.setTimeEnd(addDuration2(lstTime.get(i + 1).getBeginTime(),
						movieTimeRoom.getIdMovie().getMovieDuration() + 10));
				boolean check = true;
				// Neu la ngay hien tai
				if (isToday) {
					// Kiểm tra nếu giờ hiện tại nằm trong khoảng thời gian
					if (currentTime.toLocalTime().isAfter(timeRange.getTimeBegin().toLocalTime())
							&& currentTime.toLocalTime().isBefore(timeRange.getTimeEnd().toLocalTime())) {
						// Bắt đầu = giờ hiện tại
						timeRange.setTimeBegin(currentTime);
					}
					if (currentTime.toLocalTime().isAfter(timeRange.getTimeEnd().toLocalTime())) {
						check = false;// TH currentTime nằm sau thời gian kết thúc
					}
				}
				if (check)
					lstTimeRange.add(timeRange);
			}
		}
		// Tìm thời gian trống từ suất cuối cùng đến thời gian rạp đóng cửa
		long tmpEnd = (temp_end.getTime() - lstTime.get(lstTime.size() - 1).getEndTime().getTime()) / 60000
				- movieTimeRoom.getIdMovie().getMovieDuration() - 10;
		if (tmpEnd > 0) {
			TimeRange timeRange = new TimeRange();
			timeRange.setTimeBegin(lstTime.get(lstTime.size() - 1).getEndTime());
			timeRange.setTimeEnd(addDuration2(temp_end, movieTimeRoom.getIdMovie().getMovieDuration() + 10));
			boolean check = true;
			// Neu la ngay hien tai
			if (isToday) {
				// Kiểm tra nếu giờ hiện tại nằm trong khoảng thời gian
				if (currentTime.toLocalTime().isAfter(timeRange.getTimeBegin().toLocalTime())
						&& currentTime.toLocalTime().isBefore(timeRange.getTimeEnd().toLocalTime())) {
					// Bắt đầu = giờ hiện tại
					timeRange.setTimeBegin(currentTime);
				}
				if (currentTime.toLocalTime().isAfter(timeRange.getTimeEnd().toLocalTime())) {
					check = false;// TH currentTime nằm sau thời gian kết thúc
				}
			}
			if (check)
				lstTimeRange.add(timeRange);
		}
		List<TimeRange> tmp = new ArrayList<TimeRange>();
		for (TimeRange timeRange : lstTimeRange) {
			if (!timeRange.getTimeBegin().toLocalTime().isBefore(currentTime.toLocalTime())) {
				tmp.add(timeRange);
			}
		}
		lstTimeRange = tmp;
		return lstTimeRange;
	}

	@Override
	public List<MovieTimeRoomOfChairDTO> listTimeRange(Date date, int idRoom, int idMovie) throws Exception {
		// Xử lý idRoom và idMovie
		MovieEntity movie = movieRepository.findByIdMovie(idMovie);
		RoomEntity room = roomRepository.findByIdRoom(idRoom);
		// Danh sách suất chiếu đã tồn tại theo phòng và ngày đó
		List<MovieTimeRoomOfChairDTO> lstMovieTimeDTO = findByDateAndRoomAndStatusOrderByBeginTimeAsc(date, room);
		return lstMovieTimeDTO;
	}

	@Override
	public void saveTimeUpdate(String idMovieTimeRoom, String timeBegin, String timeEnd) throws Exception {
		MovieTimeRoomEntity movieTimeRoomEntity = movieTimeRoomRepository
				.findOneByIdMovieTimeRoom(Integer.parseInt(idMovieTimeRoom));
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		// Phân tích chuỗi thời gian
		java.util.Date dateBegin = sdf.parse(timeBegin);
		java.util.Date dateEnd = sdf.parse(timeEnd);
		// Chuyển đổi sang kiểu java.sql.Time
		Time begin = new Time(dateBegin.getTime());
		Time end = new Time(dateEnd.getTime());
		movieTimeRoomEntity.setBeginTime(begin);
		movieTimeRoomEntity.setEndTime(end);
		movieTimeRoomRepository.save(movieTimeRoomEntity);
	}

	@Override
	public int checkMovieIsUsed(int idMovie) throws Exception {
		// TODO Auto-generated method stub
		return movieTimeRoomRepository.checkMovieIsUsed(idMovie);
	}

}