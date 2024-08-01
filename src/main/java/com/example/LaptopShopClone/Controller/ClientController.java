package com.example.LaptopShopClone.Controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.ServiceImpl.NguoiDungServiceImpl;
import com.example.LaptopShopClone.ServiceImpl.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("loggedUser")
public class ClientController {
	
	@Autowired
	NguoiDungServiceImpl nguoiDungServiceImpl;
	@Autowired
	Validation validation;
	Model model1;
	
	
	@GetMapping("/home")
	public String getHomePage() {
		return "/client/home";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("nguoiDung", new NguoiDung());
		return "/client/register.html";
	}
	
	@PostMapping("/register")
	public String Register(NguoiDung nguoiDung, @RequestParam("repassword") String repassword, Model model) {
		ArrayList<String> EmailErrors=new ArrayList<String>();
		ArrayList<String> PasswordErrors=new ArrayList<String>();
		ArrayList<String> UserNameErrors=new ArrayList<String>();
		ArrayList<String> PhoneErrors=new ArrayList<String>();
		ArrayList<String> AddressErrors=new ArrayList<String>();
		ArrayList<String> ConfirmPassErrors=new ArrayList<String>();
		Boolean isAccepted=true;
		String error;
		
		
		//Validate the registeration
		
		//1 kiem tra xem email co dung form
		//2 kiem tra email da ton tai
		//3 kiem tra de trong
		//4 kiem tra mat khau va nhap lai mat khau co dong nhat
		//5 kiem tra do dai mat khau la 8-32 ki tu
		
		//Note done:
		//6 kiem tra format so dien thoai, chua lam****
		
		
		//1
//		Pattern pattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//		Matcher matcher=pattern.matcher(nguoiDung.getEmail());
//		if(!matcher.matches()) {
//			EmailErrors.add("Địa chỉ email không phù hợp");
//			isAccepted=false;
//		}
		
		error=validation.isEmailFormat(nguoiDung.getEmail());
		if(error!=null) {
			EmailErrors.add(error);
			isAccepted=false;
		}
		
		//2
//		if(this.nguoiDungServiceImpl.findByEmail(nguoiDung.getEmail())!=null) {
//			EmailErrors.add("Địa chỉ email đã được sử dụng");
//			isAccepted=false;
//		}
		error=validation.isEmailAlreadyUsed(nguoiDung.getEmail());
		if(error!=null) {
			EmailErrors.add(error);
			isAccepted=false;
		}
		
		//3 ho ten
		if(nguoiDung.getHoTen()==null) {
			UserNameErrors.add("Họ tên không được bỏ trống");
			isAccepted=false;
		}
		//3 so dien thoai
		if(nguoiDung.getSoDienThoai()==null) {
			PhoneErrors.add("Số điện thoại không được bỏ trống");
			isAccepted=false;
		}
		//3 dia chi
		if(nguoiDung.getDiaChi()==null) {
			AddressErrors.add("Địa chỉ không được bỏ trống");
			isAccepted=false;
		}
		//3 email
		if(nguoiDung.getEmail()==null) {
			EmailErrors.add("Email không được trống");
			isAccepted=false;
		}
		//3 password
		if(nguoiDung.getPassword()==null) {
			PasswordErrors.add("Password không được bỏ trống");
			isAccepted=false;
		}
		//3 re password
		if(repassword==null) {
			ConfirmPassErrors.add("Nhắc lại mật khẩu không được bỏ trống");
			isAccepted=false;
		}
		//4
		if(!nguoiDung.getPassword().equals(repassword)) {
			ConfirmPassErrors.add("Nhắc lại mật khẩu không chính xác");
			isAccepted=false;
		}
		//5
		if(nguoiDung.getPassword().length()<8 || nguoiDung.getPassword().length()>32) {
			PasswordErrors.add("Mật khẩu phải dài 8-32 ký tự");
		}
		

		model.addAttribute("EmailErrors", EmailErrors);
		model.addAttribute("PasswordErrors", PasswordErrors);
		model.addAttribute("UserNameErrors", UserNameErrors);
		model.addAttribute("PhoneErrors", PhoneErrors);
		model.addAttribute("AddressErrors", AddressErrors);
		model.addAttribute("ConfirmPassErrors", ConfirmPassErrors);
		
		if(isAccepted) {
			this.nguoiDungServiceImpl.saveUserForMember(nguoiDung);//chua xet role la member
			
		}
		else {
			return "client/register";
		}
		
		return "redirect:/login";
	}
	
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
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password, Model model, HttpServletRequest httpServletRequest) {
		String emailError=null;
		String passwordError=null;
		ArrayList<String> loginErrors=new ArrayList<String>();
		//validation
		emailError=validation.isEmailFormat(email);
		if(password==null || password=="") {
			passwordError="Password không được bỏ trống";
		}
		NguoiDung nguoiDung=this.nguoiDungServiceImpl.findByEmail(email);
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
	
	@GetMapping("/test11")
	public String test1(Model model) {
		model1=model;
		
		return "client/home";
		
	}
	
	
	
}