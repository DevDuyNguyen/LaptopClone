package com.example.LaptopShopClone.Controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
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

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceInterface.DanhMucService;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;
import com.example.LaptopShopClone.ServiceInterface.NguoiDungService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("loggedUser")
public class ClientController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	@Autowired
	Validation validation;
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	GioHangService gioaHangService;
	@Autowired
	DanhMucService danhMucService;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	@GetMapping("/home")
	public String getHomePage(Model model) {
		List<DanhMuc> danhMucList=this.danhMucService.getAllDanhMuc();
		
		model.addAttribute("danhMucList", danhMucList);
		
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
//		if(this.nguoiDungService.findByEmail(nguoiDung.getEmail())!=null) {
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
			isAccepted=false;
		}
		

		model.addAttribute("EmailErrors", EmailErrors);
		model.addAttribute("PasswordErrors", PasswordErrors);
		model.addAttribute("UserNameErrors", UserNameErrors);
		model.addAttribute("PhoneErrors", PhoneErrors);
		model.addAttribute("AddressErrors", AddressErrors);
		model.addAttribute("ConfirmPassErrors", ConfirmPassErrors);
		
		if(isAccepted) {
			this.nguoiDungService.saveUserForMember(nguoiDung);//chua xet role la member
			GioHang gioHang=new GioHang();
			gioHang.setNguoiDung(nguoiDung);
			this.gioaHangService.saveOrUpdate(gioHang);
			
		}
		else {
			return "client/register";
		}
		
		return "redirect:/login";
	}
	

	
	//danhMucID
	@GetMapping("/danhMuc")
	public String getDanhMuc(@RequestParam("danhMuc") Long danhMuc) {
		
		return "redirect:/simpleSearch?tenSanPham=&danhMuc="+danhMuc;
	}
	
	
	@GetMapping("/sp")
	public String xemChiTietSanPham(@RequestParam("id") long spID, Model model) {
		SanPham sp=this.sanPhamService.getSanPhamByID(spID);
		model.addAttribute("sp", sp);
		
		return "client/chiTietSanPham";
	}
	
	
	
	
	
}
