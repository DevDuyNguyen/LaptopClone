package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceImpl.DanhMucService;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;
import com.example.LaptopShopClone.ServiceInterface.NguoiDungService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes({"loggedUser","role_list"})

public class LoginController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	@Autowired
	Validation validation;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	@ModelAttribute("role_list")
	public List<VaiTro> getLoggedUserVaiTro(HttpServletRequest httpServletRequest) {
		NguoiDung ng=getLoggedUser(httpServletRequest);
		if(ng==null)
			return null;
					
		return getLoggedUser(httpServletRequest).getVaiTro();
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
		
		List<VaiTro> role_list=nguoiDung.getVaiTro();
				
		HttpSession httpSession=httpServletRequest.getSession();
		httpSession.setAttribute("loggedUser", nguoiDung);
		httpSession.setAttribute("role_list", role_list);
		httpSession.setMaxInactiveInterval(60*60*2);
		
		for(VaiTro v:role_list) {
			if(v.getTenVaiTro().equals("ROLE_ADMIN")) {
				return "redirect:/admin";
			}
			else if(v.getNguoiDungs().equals("ROLE_SHIPPER")) {
				return "redirect:/shipper";
			}
		}
		
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
