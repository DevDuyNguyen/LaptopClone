package com.example.LaptopShopClone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;
import com.example.LaptopShopClone.Utils.Validation;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")

@SessionAttributes({"loggedUser","role_list"})
public class AdminController {
	@Autowired
	DonHangService donHangService;
	@Autowired
	Validation validation;
	@Autowired
	VaiTroService vaiTroService;
	
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
	
	@GetMapping("")
	public String getAdminPage(Model model, @ModelAttribute("role_list") List<VaiTro> role_list) {
		//Đang chờ duyệt
		//Đang chờ giao
		//
		
		String memberAcess=this.validation.manageRoleAccess(role_list,vaiTroService.getVaiTroByName("ROLE_ADMIN"));
		if(!memberAcess.equals("ok")) {
			return memberAcess;
		}
		
		int soLuongDonHangMoi=this.donHangService.soLuongDonHangByTrangThai("Đang chờ giao");
		int soLuongDonHangCanPheDuyet=this.donHangService.soLuongDonHangByTrangThai("Đang chờ duyệt");;
		
		model.addAttribute("soLuongDonHangMoi", soLuongDonHangMoi);
		model.addAttribute("soLuongDonHangCanPheDuyet", soLuongDonHangCanPheDuyet);
		
		return "Admin/home";
		
	}
	
}
