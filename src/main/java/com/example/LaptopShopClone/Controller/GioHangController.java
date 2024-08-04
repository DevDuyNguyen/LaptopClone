package com.example.LaptopShopClone.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceInterface.GioHangService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

class SanPhamAndSoLuong{
	SanPham sanPham;
	Integer soLuong;
	
	
	
	public SanPhamAndSoLuong(SanPham sanPham, Integer soLuong) {
		super();
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public Integer getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
	
	
}

@Controller
@RequestMapping("/gio-hang")
@SessionAttributes({"loggedUser"})

public class GioHangController {
	@Autowired
	GioHangService gioHangService;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getLoggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	NguoiDung getLoggedInUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	@GetMapping()
	public String getGioHangPage(HttpServletRequest httpServletRequest, Model model) {
		NguoiDung loggedUser=this.getLoggedInUser(httpServletRequest);
		if(loggedUser==null) {
			model.addAttribute("loggedUserNotExist", true);
			return "Client/gioHang";
		}
		
		GioHang gioHang=this.gioHangService.getGioHangByNguoiDung(loggedUser);
		List<ChiMucGioHang> chiMucGioHangList=gioHang.getChiMucGioHangs();
		List<SanPhamAndSoLuong> sanPhamAndSoLuongs=new ArrayList<SanPhamAndSoLuong>();
		Boolean isCartEmpty=true;
		if(chiMucGioHangList.size()>0)
			isCartEmpty=false;
		
		for(ChiMucGioHang chiMucGioHang:chiMucGioHangList) {
			sanPhamAndSoLuongs.add(new SanPhamAndSoLuong(chiMucGioHang.getSanPham(), chiMucGioHang.getSo_luong()));
		}
		
		model.addAttribute("sanPhamAndSoLuongs", sanPhamAndSoLuongs);
		model.addAttribute("isCartEmpty", isCartEmpty);
		
		
		return "Client/gioHang";
	}
	
	
	
}
