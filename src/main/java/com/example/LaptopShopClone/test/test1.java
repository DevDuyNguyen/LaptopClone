package com.example.LaptopShopClone.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.Repository.NguoiDungRepository;
import com.example.LaptopShopClone.Repository.VaiTroRepository;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("name")
public class test1 {
	@Autowired
	NguoiDungRepository nguoiDungRepository;
	@Autowired 
	SessionFactoryUtil sessionFactoryUtil;
	@Autowired
	VaiTroRepository vaiTroRepository;
	@Autowired 
	SanPhamService sanPhamService;
	
	
	@GetMapping("/test1")
	public String simpleTest1() {
		SanPham sp=this.sanPhamService.getSanPhamByID(3);
		System.out.println(sp);
				
		return "test1";
	}
	@GetMapping("/test2")
	public String simpleTest2(Model model, HttpServletRequest httpServletRequest) {
		model.asMap().remove("name");
		httpServletRequest.getSession().invalidate();
		
		return "test1";
	}
	@GetMapping("/test3")
	public String simpleTest3(Model model, HttpServletRequest httpServletRequest) {
		System.out.println(model.getAttribute("name"));
		System.out.println(httpServletRequest.getSession().getAttribute("name"));
		
		return "test1";
	}
	
}
