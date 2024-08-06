package com.laptrinhjavaweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	// Dang ki voi method post
		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ModelAndView dangki(@ModelAttribute UserDTO userDTO, HttpServletRequest request,
				HttpServletResponse response) {
			try {
				// client gui len server bang utf-8
				request.setCharacterEncoding("utf-8");
				// server gui len client bang utf-8
				response.setCharacterEncoding("utf-8");
				ModelAndView registerView = new ModelAndView("customer/register");
				String repeat_password = request.getParameter("repeat_password");
				long millis = System.currentTimeMillis();
				java.util.Date today = new java.util.Date(millis);
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				String dateView = request.getParameter("dateView");
				String message = "", message_email = "", message_fullName = "", mess_phone = "", message_address = "",
						message_dateOfBirth = "", message_password = "", message_repeat_password = "";
				// Nếu nhập đầy đủ các trường...
//				if (!userDTO.getEmail().equals("") && !userDTO.getFullName().equals("") && !userDTO.getPhone().equals("")
//						&& !userDTO.getAddress().equals("") && !dateView.equals("") && !userDTO.getPassword().equals("")
//						&& !repeat_password.equals("")) {
				
				// Nếu nhập đầy đủ các trường có dấu sao
				if (!userDTO.getEmail().equals("") && !userDTO.getFullName().equals("") && !userDTO.getPassword().equals("")
						&& !repeat_password.equals("") &&( userDTO.getPhone().equals("") ||userDTO.getAddress().equals("") || dateView.equals(""))) {
					Boolean check = true;
					if (repeat_password.equals(userDTO.getPassword())) {
						// Kiểm tra định dạng email
						String regex_email = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b";
						Pattern pattern_email = Pattern.compile(regex_email);
						Matcher matcher_email = pattern_email.matcher(userDTO.getEmail());
						if (matcher_email.find() == false) {
							message_email = "Vui lòng nhập đúng định dạng email!";
							request.setAttribute("message_email", message_email);
							check = false;
						}
						// Nếu định dạng email đúng thì kiểm tra tiếp..
						if (check == true) {
							// Kiểm tra người dùng đã đăng kí chưa
							if (userService.ktDangKi(userDTO.getEmail()) == null) {
								userService.dangKi(userDTO);
								message = "Đăng kí thành công!";
								request.setAttribute("message", message);

							} else {
								message = "Tài khoản đã được đăng kí!";
								request.setAttribute("message", message);

							}
						}
					} else {// Mật khẩu nhập lại không đúng
						message_repeat_password = "Mật khẩu nhập lại không đúng";
						request.setAttribute("message_repeat_password", message_repeat_password);
					}
					return registerView;
				}

				if (!userDTO.getEmail().equals("") && !userDTO.getFullName().equals("") && !userDTO.getPhone().equals("")
	&& !userDTO.getAddress().equals("") && !dateView.equals("") && !userDTO.getPassword().equals("")
						&& !repeat_password.equals("")) {
					Date dateOfBirth = f.parse(dateView);
					Boolean check = true;
					// Kiểm tra nhập lại mật khẩu
					if (repeat_password.equals(userDTO.getPassword())) {
						// Kiểm tra tính hợp lệ của ngày tháng năm sinh
						if (dateOfBirth.compareTo(today) >= 0) {
							message_dateOfBirth = "Ngày sinh không hợp lệ!";
							request.setAttribute("message_dateOfBirth", message_dateOfBirth);
							check = false;
						}
						// Kiểm tra định dạng email
						String regex_email = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b";
						Pattern pattern_email = Pattern.compile(regex_email);
						Matcher matcher_email = pattern_email.matcher(userDTO.getEmail());
						if (matcher_email.find() == false) {
							message_email = "Vui lòng nhập đúng định dạng email!";
							request.setAttribute("message_email", message_email);
							check = false;
						}
						// Kiểm tra định dạng số điện thoại
						String regex_phone = "^(84|0)\\d{9,10}$";
						Pattern pattern_phone = Pattern.compile(regex_phone);
						Matcher matcher_phone = pattern_phone.matcher(userDTO.getPhone());
						if (matcher_phone.find() == false) {
							mess_phone = "Vui lòng nhập đúng định dạng số điện thoại!";
							request.setAttribute("mess_phone", mess_phone);
							check = false;
						}
						// Nếu định dạng email và sđt đúng thì kiểm tra tiếp..
						if (check == true) {
							// Kiểm tra người dùng đã đăng kí chưa
							if (userService.ktDangKi(userDTO.getEmail()) == null) {
								userDTO.setDateOfBirth(dateOfBirth);
								userService.dangKi(userDTO);
								message = "Đăng kí thành công!";
								request.setAttribute("message", message);

							} else {
								message = "Tài khoản đã được đăng kí!";
								request.setAttribute("message", message);
								request.setAttribute("check", "true");

							}
						}
					} else {// Mật khẩu nhập lại không đúng
						message_repeat_password = "Mật khẩu nhập lại không đúng";
						request.setAttribute("message_repeat_password", message_repeat_password);
					}
					return registerView;
				} else {
					if (userDTO.getEmail().equals("")) {
						message_email = "Email không được bỏ trống!";
						request.setAttribute("message_email", message_email);
					}
					if (userDTO.getFullName().equals("")) {
						message_fullName = "Họ và tên không được bỏ trống!";
						request.setAttribute("message_fullName", message_fullName);
					}
//					if (userDTO.getAddress().equals("")) {
//						message_address = "Địa chỉ không được bỏ trống!";
//						request.setAttribute("message_address", message_address);
//					}
//					if (dateView.equals("")) {
//						message_dateOfBirth = "Ngày sinh không được bỏ trống!";
//						request.setAttribute("message_dateOfBirth", message_dateOfBirth);
//					}
					if (userDTO.getPassword().equals("")) {
						message_password = "Mật khẩu không được bỏ trống!";
						request.setAttribute("message_password", message_password);
					}
//					if (userDTO.getPhone().equals("")) {
//						mess_phone = "Số điện thoại không được bỏ trống!";
//						request.setAttribute("mess_phone", mess_phone);
//					}
					if (repeat_password.equals("") && !userDTO.getPassword().equals("")) {
						message_repeat_password = "Nhập lại mật khẩu!";
						request.setAttribute("message_repeat_password", message_repeat_password);
					}
				}

				return registerView;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				ModelAndView registerView = new ModelAndView("customer/register");
				return registerView;
			}

		}

	// Dang ki voi method get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView dangki() {
		ModelAndView mav = new ModelAndView("customer/register");
		return mav;
	}

	// phân trang nha mn
	@RequestMapping(value = "/admin/qladmin", method = RequestMethod.GET)
	public ModelAndView qladmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("admin/user");

		try {
			List<UserDTO> listuser;
			String key = request.getParameter("txtsearch");
			
			mav.addObject("key", key);
			int page = 0; // Trang mặc định
			int size = 5; // Số lượng phần tử trên mỗi trang
			String pageStr = request.getParameter("page");
			if (pageStr != null && !pageStr.isEmpty()) {
				page = Integer.parseInt(pageStr);
			}
			mav.addObject("currentPage", page);
			
			if (key == null || key.isEmpty()) {
				listuser = userService.getAllUser();
			} else {
				listuser = userService.getUserByName(key);

			}
			// Xóa tài khoản QTV khỏi danh sách
			Iterator<UserDTO> iterator = listuser.iterator();
			while (iterator.hasNext()) {
				UserDTO user = iterator.next();
				if (user.getFullName().equals("ADMIN")) {
					iterator.remove();
				}
			}
			// Xóa tài khoản QTV khỏi danh sách
			
			// listuser.remove(userService.getUserById(19));
			// Xóa tài khoản QTV khỏi danh sách

			// Tính chỉ mục bắt đầu và kết thúc cho trang hiện tại
			int start = page * size;
			int end = Math.min(start + size, listuser.size());

			// Lấy chỉ mục từ start đến end của listuser
			List<UserDTO> paginatedUsers = listuser.subList(start, end);

			mav.addObject("listuser", paginatedUsers);
			mav.addObject("totalPages", (int) Math.ceil((double) listuser.size() / size));
			mav.addObject("totalrow", (int) Math.ceil((double) listuser.size()));
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
	}
}
