package com.laptrinhjavaweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.EncryptUtils;
import com.laptrinhjavaweb.utils.SecurityUtils;

@Controller
public class ChangePasswordController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public ModelAndView ChangePasswordPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("customer/changepassword");
		try {

		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("customer/changepassword");
		}
		return mav;
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public ModelAndView ChangePassword(@RequestParam(value = "oldPassword", required = false) String oldPassword,
			@RequestParam(value = "newPassword", required = false) String newPassword,
			@RequestParam(value = "confirmPassword", required = false) String confirmPassword,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("customer/changepassword");
		String alert_oldpass ="";
		String alert_newpass="";
		String alert_confirmpass="";
		String alert_pass="";
		try {
			String email = SecurityUtils.getPrincipal().getEmail();
			UserDTO userDTO = userService.findByEmail(email);
			EncryptUtils encryptUtils = new EncryptUtils();
			String pass = encryptUtils.ecrypt(oldPassword);
			if(!oldPassword.equals("") && !newPassword.equals("") && !confirmPassword.equals("")) {
				if (pass.equals(userDTO.getPassword())) {
					if (newPassword.equals(oldPassword)) {
						alert_pass = "Mật khẩu mới không trùng mật khẩu cũ";
						mav.addObject("alert_pass",alert_pass);
					}else {
						if (newPassword.equals(confirmPassword)) {
							userService.changePassword(userDTO.getIdUser(), newPassword);
							return new ModelAndView("redirect:/logout");
						} else {
							alert_confirmpass =  "Mật khẩu nhập lại chưa đúng.";
							//mav.addObject("alert_confirmpass", "Mật khẩu nhập lại chưa đúng.");
						}
					}
				} else {
					alert_oldpass = "Mật khẩu không đúng.";
					//mav.addObject("checkPass", "Mật khẩu không đúng.");
				}
			}else {
				if(oldPassword.equals("")) {
					alert_oldpass = "Vui lòng nhập mật khẩu cũ.";
				}
				if(newPassword.equals("")) {
					alert_newpass = "Vui lòng nhập mật khẩu mới.";
				}
				if (confirmPassword.equals("")) {
					alert_confirmpass = "Vui lòng nhập lại mật khẩu mới.";
				}
			}
			mav.addObject("alert_oldpass", alert_oldpass);
			mav.addObject("alert_newpass",alert_newpass);
			mav.addObject("alert_confirmpass", alert_confirmpass);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("customer/changepassword");
		}
		return mav;
	}
}