package com.laptrinhjavaweb.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laptrinhjavaweb.dto.MovieDTO;
import com.laptrinhjavaweb.dto.MovieEntityShowTime;
import com.laptrinhjavaweb.dto.MovieTypeDTO;
import com.laptrinhjavaweb.entity.MovieTypeEntity;
import com.laptrinhjavaweb.service.IMovieService;
import com.laptrinhjavaweb.service.IMovieTypeService;

@Controller
public class saveMovieController {
	@Autowired
	IMovieTypeService movieTypeService;
	
	@Autowired
	IMovieService movieService;
	
	String mainImageBase64="";
	String thumnailBase64="";
	
	private static final Logger logger = LoggerFactory
			.getLogger(saveMovieController.class);
	
	//Show thêm phim
	@RequestMapping(value = "admin/show-add-movie", method = RequestMethod.GET)
	public ModelAndView showAddMovie(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("admin/movie_add");
		try {
			String message = request.getParameter("message");
			if(message != null) {
				request.setAttribute("mainImage", request.getParameter("mainImage"));
				request.setAttribute("mainImageBase64", mainImageBase64);
				request.setAttribute("thumnail", request.getParameter("thumnail"));
				request.setAttribute("thumnailBase64", thumnailBase64);
				
				request.setAttribute("idMovie", request.getParameter("idMovie"));
				request.setAttribute("status", request.getParameter("status"));
				request.setAttribute("idMovieType", request.getParameter("idMovieType"));
				request.setAttribute("movieName", request.getParameter("movieName"));
				request.setAttribute("movieDate", request.getParameter("movieDate"));
				request.setAttribute("movieDuration", request.getParameter("movieDuration"));
				request.setAttribute("director", request.getParameter("director"));
				request.setAttribute("actors", request.getParameter("actors"));
				request.setAttribute("movieScript", request.getParameter("movieScript"));
				request.setAttribute("movieFormat", request.getParameter("movieFormat"));
				
				request.setAttribute("message", message);
			}
			
			List<MovieTypeDTO> listMovieType = movieTypeService.getAllMovieType();
			long newIdMovie = movieService.getMaxMovieId() + 1;
			mav.addObject("newIdMovie",newIdMovie);
			mav.addObject("listMovieType", listMovieType);
		} catch (Exception e) {
			mav.addObject("errorMessage", "Có lỗi xảy ra khi thêm phim.");
            e.printStackTrace();
		}
		return mav;
	}
	
	
	//Show Sửa phim
	@RequestMapping(value = "admin/show-edit-movie", method = RequestMethod.GET)
	public ModelAndView showAddMovie(
			@RequestParam("idMovie") int idMovie,
			HttpServletRequest request){
		ModelAndView mav = new ModelAndView("admin/movie_edit");
		try {
			String message = request.getParameter("message");
			if(message != null) {
				request.setAttribute("mainImage", request.getParameter("mainImage"));
				request.setAttribute("mainImageBase64", mainImageBase64);
				request.setAttribute("thumnail", request.getParameter("thumnail"));
				request.setAttribute("thumnailBase64", thumnailBase64);
				
//				request.setAttribute("status", request.getParameter("status"));
//				request.setAttribute("idMovieType", request.getParameter("idMovieType"));
//				request.setAttribute("movieName", request.getParameter("movieName"));
//				request.setAttribute("movieDate", request.getParameter("movieDate"));
//				request.setAttribute("movieDuration", request.getParameter("movieDuration"));
//				request.setAttribute("director", request.getParameter("director"));
//				request.setAttribute("actors", request.getParameter("actors"));
//				request.setAttribute("movieScript", request.getParameter("movieScript"));
//				request.setAttribute("movieFormat", request.getParameter("movieFormat"));
				
				request.setAttribute("message", message);
			}
			
			//Hàm có sẵn, tái sử dụng được cho việc hiển thị thông tin phim
			MovieEntityShowTime movie = movieService.getMovieById(idMovie);
			List<MovieTypeDTO> listMovieType = movieTypeService.getAllMovieType();
			
			mav.addObject("idMovie", idMovie);
			mav.addObject("movie", movie);
			mav.addObject("listMovieType", listMovieType);
		} catch (Exception e) {
			mav.addObject("errorMessage", "Có lỗi xảy ra khi thêm phim.");
            e.printStackTrace();
		}
		return mav;
	}
	
	
	//
	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "admin/save-movie", method = RequestMethod.POST)
	public String uploadMultipleFileHandler(
			@RequestParam("file") MultipartFile[] files,
			@RequestParam("isEdit") Boolean isEdit,
			@RequestParam("idMovie") int idMovie,
			@RequestParam(value = "status", defaultValue = "0") Integer status,
			@RequestParam("idMovieType") int idMovieType,
			@RequestParam("movieName") String movieName,
			@RequestParam("movieDate") String movieDate,
			@RequestParam("movieDuration") int movieDuration,
			@RequestParam("director") String director,
			@RequestParam("actors") String actors,
			@RequestParam("movieScript") String movieScript,
			@RequestParam("movieFormat") String movieFormat,
			@RequestParam(value = "mainImage", required = false) String mainImage,
			@RequestParam(value = "thumnail", required = false) String thumnail,
			RedirectAttributes redirectAttributes) {
		String message = "";
		try {
			// Chuyển đổi movieDate từ String thành Date
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(movieDate);
            String movieDateOld = movieDate; 
            if(movieDate.equals("dd/MM/yyyy")) {
            	movieDate = "01/02/9999";
            }
            Date startDate = dateFormat2.parse(movieDate);
            
            // Lấy ngày hiện tại
            Date currentDate2 = new Date();
			if(movieDate.equals("01/02/9999") || (startDate.before(currentDate2)&& !isEdit) || (startDate.equals(currentDate2) && !isEdit) || movieDuration <= 0 || movieName.trim().isEmpty() || movieName == null  
					   || director == null || director.trim().isEmpty() || actors == null || actors.trim().isEmpty() || movieScript == null || movieScript.trim().isEmpty() 
					   || movieFormat == null || movieFormat.trim().isEmpty() || idMovieType <= 0 || movieDate == null || movieDate.trim().isEmpty()) {
						
				if(movieDate.equals("01/02/9999")) {
					redirectAttributes.addAttribute("message", "Lưu phim không thành công! Bạn chưa nhập ngày khởi chiếu.");
				}
				else if(movieDuration <= 0) {
					redirectAttributes.addAttribute("message", "Lưu phim không thành công! Thời gian nhập vào không được nhỏ hơn hoặc bằng 0.");
				}
				else if (movieName.trim().isEmpty() || movieName == null || movieDate == null || movieDate.trim().isEmpty() || director == null || director.trim().isEmpty() || actors == null || actors.trim().isEmpty() || movieScript == null || movieScript.trim().isEmpty()) {
					redirectAttributes.addAttribute("message", "Lưu phim không thành công! Có trường nhập toàn ký tự trắng.");
				}
				else if (idMovieType <= 0) {
					redirectAttributes.addAttribute("message", "Lưu phim không thành công! Bạn chưa chọn loại phim.");
				} 
				else if ((startDate.before(currentDate2)&& !isEdit) || (startDate.equals(currentDate2) && !isEdit)){
                    redirectAttributes.addAttribute("message", "Lưu phim không thành công! Ngày khởi chiếu không hợp lệ.");
				}
				else if (movieFormat == null || movieFormat.trim().isEmpty()) {
					redirectAttributes.addAttribute("message", "Lưu phim không thành công! Bạn chưa chọn định dạng phim.");
				}
				
				mainImageBase64 = new String(Base64.getEncoder().encode(files[0].getBytes()));
				thumnailBase64 = new String(Base64.getEncoder().encode(files[1].getBytes()));

                redirectAttributes.addAttribute("mainImage", files[0].getOriginalFilename());
                redirectAttributes.addAttribute("thumnail", files[1].getOriginalFilename());
                redirectAttributes.addAttribute("idMovie", idMovie);
                redirectAttributes.addAttribute("status", status);
                redirectAttributes.addAttribute("idMovieType", idMovieType);
                redirectAttributes.addAttribute("movieName", movieName);
                redirectAttributes.addAttribute("movieDate", movieDateOld);
                redirectAttributes.addAttribute("movieDuration", movieDuration);
                redirectAttributes.addAttribute("director", director);
                redirectAttributes.addAttribute("actors", actors);
                redirectAttributes.addAttribute("movieScript", movieScript);
                redirectAttributes.addAttribute("movieFormat", movieFormat);
                
                
                if(isEdit) {
                	return "redirect:/admin/show-edit-movie";
                }
                return "redirect:/admin/show-add-movie";
                
			}
			
			MovieDTO movieDto = new MovieDTO();
			
			MovieTypeEntity movieTypeEntity = new MovieTypeEntity();
			movieTypeEntity.setIdMovieType(idMovieType);
			
			// Định dạng đầu vào của movieDate
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			// Định dạng đầu ra mong muốn
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = inputFormat.parse(movieDate);
			String formattedDate = dateFormat.format(date);
			
			movieDto.setIdMovie(idMovie);
			movieDto.setStatus(status);
			movieDto.setIdMovieType(movieTypeEntity);
			movieDto.setMovieName(movieName);
			movieDto.setMovieDate(date);
			movieDto.setMovieDuration(movieDuration);
			movieDto.setDirector(director);
			movieDto.setActors(actors);
			movieDto.setMovieScript(movieScript);
			movieDto.setMovieFormat(movieFormat);
			
			
			
			if(!files[0].getOriginalFilename().isEmpty() || !files[1].getOriginalFilename().isEmpty()) {
				boolean isFirstLoop = true;
				int fileIndex = 0; // Biến đếm để theo dõi vị trí của file trong mảng
				for (MultipartFile file : files) {
					if(!file.getOriginalFilename().isEmpty()) {
						byte[] bytes = file.getBytes();
						
						// Lấy tên và phần mở rộng của file
						SimpleDateFormat dateFormatName = new SimpleDateFormat("yyyyMMdd_HHmmss");
						String timestamp = dateFormatName.format(new Date());
						String fileName = timestamp + "-" + file.getOriginalFilename();
						
						System.out.println(fileName);
						
						if(isFirstLoop) {
						    // Đặt giá trị cho mainImage nếu là phần tử đầu tiên
						    movieDto.setMainImage("sliders/"+fileName);
						} else {
						    // Đặt giá trị cho thumbnail nếu không phải phần tử đầu tiên
						    movieDto.setThumnail("posters/"+fileName);
						}
						// Tạo thư mục để lưu file
						String rootPath = "C:\\Users\\dmx\\OneDrive\\Desktop\\RapChieuPhim_051524\\RapChieuPhim_SpringMVC\\src\\main\\webapp\\template\\customer\\img\\";
//						String rootPath = "D:\\RapChieuPhim_051524\\RapChieuPhim_SpringMVC\\src\\main\\webapp\\template\\customer\\img\\";
						String subFolder = isFirstLoop ? "sliders\\" : "posters\\";
						File dir = new File(rootPath + subFolder);
						if (!dir.exists())
						    dir.mkdirs();
						// Tạo file trên server
						File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
						BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();

						logger.info("Server File Location=" + serverFile.getAbsolutePath());

						isFirstLoop = false;
					} else {
			            // Nếu file là null, gán giá trị cho mainImage hoặc thumbnail dựa trên vị trí của file
			            if(fileIndex == 0) {
			                movieDto.setMainImage(mainImage);
			                System.out.println(mainImage);
			                isFirstLoop = false;
			            } else if(fileIndex == 1) {
			                movieDto.setThumnail(thumnail);
			                System.out.println(thumnail);
			            }
			        }
					fileIndex++; // Tăng biến đếm sau mỗi vòng lặp
				}
			}
			else {
				movieDto.setMainImage(mainImage);
				movieDto.setThumnail(thumnail);
			}
			movieService.saveMovie(movieDto);
			if(isEdit) {
				message = message + "Bạn đã cập nhật phim "+movieName+ " thành công";				
			} else {
				message = message + "Bạn đã thêm phim "+movieName+ " thành công";
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			//return new ModelAndView("admin/movie_add", "errorMessage", "Failed to upload file: " + e.getMessage());
		}
		//return new ModelAndView("/trang-chu", "uploadMessage", message);
		redirectAttributes.addAttribute("message", message);
		return "redirect:/admin/qlmovie";
	}
}
