package com.example.LaptopShopClone.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;
import com.example.LaptopShopClone.Utils.ResponseObject;
import com.example.LaptopShopClone.Utils.SearchSanPhamCriteria;
import com.example.LaptopShopClone.Utils.Validation;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/sanpham")
@SessionAttributes({"loggedUser","role_list"})
public class sanPhamAPI {
	@Autowired
	Validation validation;
	@Autowired
	VaiTroService vaiTroService;
	@Autowired
	DonHangService donHangService;
	@Autowired
	ChiTietDonHangService chiTietDonHangService;
	@Autowired
	SanPhamService sanPhamService;
	
	
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
	
	@GetMapping("/test")
	public String test() {
		return "oke";
	}
//	
//	@RequestParam(name="danhMucId", defaultValue = "0", required = false) long danhMucId,
//	@RequestParam(name="hangSXId", defaultValue = "", required = false) long hangSXId,
//	@RequestParam(name="minValue", defaultValue = "0", required = false) int minValue,
//	@RequestParam(name="maxValue", defaultValue ="-1", required = false) int maxValue,
//	@RequestParam(name="sapXepTheo", defaultValue = "", required = false) String sapXepTheo,
//	@RequestParam(name="searchByName", defaultValue = "", required = false) String searchByName,
//	@RequestParam(name="searchById", defaultValue = "0", required = false) long searchById	
	
	@GetMapping("/searchSanPham")
	public ResponseEntity<ResponseObject> searchSanPham(
			@RequestParam(name="danhMucId", defaultValue = "0") long danhMucId,
			@RequestParam(name="hangSXId", defaultValue = "0") long hangSXId,
			@RequestParam(name="minValue", defaultValue = "0") int minValue,
			@RequestParam(name="maxValue", defaultValue ="-1") int maxValue,
			@RequestParam(name="sapXepTheo", defaultValue = "") String sapXepTheo,
			@RequestParam(name="searchByName", defaultValue = "") String searchByName,
			@RequestParam(name="searchById", defaultValue = "0") long searchById,
			@RequestParam(name="currentPage", defaultValue = "1") int currentPage
			){
		
		
		int noResultPerPage=8;
		int noPage=5;
		
		if(searchById!=0) {
			ResponseObject ro=new ResponseObject();
			ro.setStatus("success");
			ArrayList<SanPham> content=new ArrayList<SanPham>();
			content.add(this.sanPhamService.getSanPhamByID(searchById));
			ro.setData(content);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
		}
		SearchSanPhamCriteria searchSanPhamCriteria=new SearchSanPhamCriteria();
		searchSanPhamCriteria.setDanhMuc(danhMucId);
		searchSanPhamCriteria.setHsx(hangSXId);
		searchSanPhamCriteria.setMinValue(minValue);
		searchSanPhamCriteria.setMaxValue(maxValue);
		searchSanPhamCriteria.setSort(sapXepTheo);
		searchSanPhamCriteria.setKeyWord(searchByName.split(" "));
		List<SanPham> sanPhamList=this.sanPhamService.searchSanPham(searchSanPhamCriteria, currentPage, noResultPerPage);

		int totalPage=this.sanPhamService.getTotalResultCount(searchSanPhamCriteria);
		totalPage=(int)Math.ceil((double)totalPage/noResultPerPage);
		
		
		ResponseObject ro=new ResponseObject();
		ro.setStatus("success");
		ro.setData(sanPhamList);
		ro.setCurrentPage(currentPage);
		ro.setTotalPage(totalPage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
	}
	
	@GetMapping("/getSoLuongSanPhamCriteria")
	public ResponseEntity<ResponseObject> getSoLuongSanPhamCriteria(
			@RequestParam(name="danhMucId", defaultValue = "0") long danhMucId,
			@RequestParam(name="hangSXId", defaultValue = "0") long hangSXId,
			@RequestParam(name="minValue", defaultValue = "0") int minValue,
			@RequestParam(name="maxValue", defaultValue ="-1") int maxValue,
			@RequestParam(name="sapXepTheo", defaultValue = "") String sapXepTheo,
			@RequestParam(name="searchByName", defaultValue = "") String searchByName,
			@RequestParam(name="searchById", defaultValue = "0") long searchById,
			@RequestParam(name="currentPage", defaultValue = "1") int currentPage
			){
		
		SearchSanPhamCriteria searchSanPhamCriteria=new SearchSanPhamCriteria();
		searchSanPhamCriteria.setDanhMuc(danhMucId);
		searchSanPhamCriteria.setHsx(hangSXId);
		searchSanPhamCriteria.setMinValue(minValue);
		searchSanPhamCriteria.setMaxValue(maxValue);
		searchSanPhamCriteria.setSort(sapXepTheo);
		searchSanPhamCriteria.setKeyWord(searchByName.split(" "));
		
		ResponseObject ro=new ResponseObject();
		ro.setStatus("success");
		ro.setData(this.sanPhamService.getTotalResultCount(searchSanPhamCriteria));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ro);
	}
	
	
	
}
