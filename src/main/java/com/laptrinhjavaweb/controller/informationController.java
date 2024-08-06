package com.laptrinhjavaweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.SecurityUtils;

@Controller
public class informationController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView informationPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("customer/information");

		try {
			
			int id = SecurityUtils.getPrincipal().getIdUser();
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
			UserDTO user = userService.getUserById(id);
			String ht = user.getFullName();
			Date date = user.getDateOfBirth();
			String dateFormat= f.format(date);
			String sdt = user.getPhone();
			String emailOriginal = user.getEmail();
			mav.addObject("emailOriginal", emailOriginal);
			mav.addObject("ht",ht);
			mav.addObject("date", dateFormat);
			mav.addObject("sdt", sdt);
			
			
			String email = request.getParameter("email");
			String fullName = request.getParameter("fullName");
			String dobstr = request.getParameter("dateOfBirth");
			String phone = request.getParameter("phone");
			

			long millis = System.currentTimeMillis(); 
			java.util.Date today = new java.util.Date(millis);
			
			String message="", message_email="", message_fullName="", mess_phone="", message_dateOfBirth="", message_error="";
			if (!email.equals("") && !email.isEmpty() && !fullName.equals("") && !dobstr.equals("") && !phone.equals("")) {
				Date dateOfBirth = f.parse(dobstr);
				Boolean check=true;
				
				//Kiểm tra tính hợp lệ của ngày tháng năm sinh
				if (dateOfBirth.compareTo(today) >= 0) {
					message_dateOfBirth = "Ngày sinh không hợp lệ!";
					request.setAttribute("message_dateOfBirth", message_dateOfBirth);
					check = false;
				}
				//Kiểm tra định dạng email
				String regex_email = "\\b[A-Za-z0-9._%+-]+@gmail\\.com\\b";
				Pattern pattern_email = Pattern.compile(regex_email);
				Matcher matcher_email = pattern_email.matcher(email);
				
				if (matcher_email.find() == false) {
					message_email = "Vui lòng nhập đúng định dạng email!";
					request.setAttribute("message_email", message_email);
					check = false;
				}
				//Kiểm tra định dạng số điện thoại
				String regex_phone = "^(84|0)\\d{9,10}$";
				Pattern pattern_phone = Pattern.compile(regex_phone);
				Matcher matcher_phone = pattern_phone.matcher(phone);
				if (matcher_phone.find() == false) {
					mess_phone = "Vui lòng nhập đúng định dạng số điện thoại!";
					request.setAttribute("mess_phone", mess_phone);
					check = false;
				}
				//Nếu định dạng email và sđt đúng thì:
				if (check == true ) {
						userService.updateUser( dateOfBirth, email, fullName, phone, id);
						message = "Cập nhật thành công!";
						request.setAttribute("message", message);
						
						user = userService.getUserById(id);
						mav.addObject("emailOriginal", user.getEmail());
						return mav;
				}
				
				return mav;
			} else {
//				if (email.equals("") || fullName.equals("") || dobstr.equals("") || phone.equals("")) {
//					message_error = "Vui lòng nhập đầy đủ thông tin!";
//					request.setAttribute("message_error", message_error);
//				}
				
				if (email.equals("")) {
					message_email = "Email không được bỏ trống!";
					request.setAttribute("message_email", message_email);
				}
				if (fullName.equals("")) {
					message_fullName = "Họ và tên không được bỏ trống!";
					request.setAttribute("message_fullName", message_fullName);
				}
				
				if (dobstr.equals("")) {
					message_dateOfBirth = "Ngày sinh không được bỏ trống!";
					request.setAttribute("message_dateOfBirth", message_dateOfBirth);
				}
				
				if (phone.equals("")) {
					mess_phone = "Số điện thoại không được bỏ trống!";
					request.setAttribute("mess_phone", mess_phone);
				}
			}
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return mav;
		}
	}
}
