package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.ServiceImpl.DanhMucService;
import com.example.LaptopShopClone.ServiceImpl.Validation;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;
import com.example.LaptopShopClone.ServiceInterface.NguoiDungService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	@Autowired
	Validation validation;
	
	@GetMapping("/login")
	public String getLoginPage(HttpServletRequest httpServletRequest) {
		HttpSession httpSession=httpServletRequest.getSession();
		NguoiDung loggedUser=(NguoiDung)httpSession.getAttribute("loggedUser");
		if(loggedUser!=null) {
			return "redirect:/home";
		}
		
		return "/client/login.html";
	}
	@PostMapping("/login")
	public String login(@RequestParam(name = "email", defaultValue = "d1@gmail.com") String email, @RequestParam(name = "password", defaultValue = "1234567890") String password, Model model, HttpServletRequest httpServletRequest) {
		String emailError=null;
		String passwordError=null;
		ArrayList<String> loginErrors=new ArrayList<String>();
		//validation
		emailError=validation.isEmailFormat(email);
		if(password==null || password=="") {
			passwordError="Password không được bỏ trống";
		}
		NguoiDung nguoiDung=this.nguoiDungService.findByEmail(email);
		if(nguoiDung==null) {
			loginErrors.add("Tài khoản không tồn tại");
		}
		else {
			if(!password.equals(nguoiDung.getPassword()))
				loginErrors.add("Mật khẩu không chính xác");
		}
		
		model.addAttribute("emailError", emailError);
		model.addAttribute("passwordError", passwordError);
		model.addAttribute("loginErrors", loginErrors);
		
		if(emailError!=null || passwordError!=null || loginErrors.size()!=0) {
			return "redirect:/login";
		}
		
				
		HttpSession httpSession=httpServletRequest.getSession();
		httpSession.setAttribute("loggedUser", nguoiDung);
		httpSession.setMaxInactiveInterval(60*60*2);
		
		return "redirect:/home";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest httpServletRequest,Model model) {
		
		HttpSession httpSession=httpServletRequest.getSession();
		
		NguoiDung nguoiDung=(NguoiDung)model.getAttribute("loggedUser");
		System.out.println(nguoiDung);
		
		if(httpSession!=null) {
			model.asMap().remove("loggedUser");
			httpSession.invalidate();
			System.out.println("Session is invalidated");
		}
		
		
		return "redirect:/home";
	}
	
}
