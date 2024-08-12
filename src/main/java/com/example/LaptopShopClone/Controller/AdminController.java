package com.example.LaptopShopClone.Controller;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.ServiceInterface.VaiTroService;
import com.example.LaptopShopClone.Utils.Pagination;
import com.example.LaptopShopClone.Utils.SearchDonHangCriteria;
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
	public String getAdminPage(Model model, @ModelAttribute("role_list") List<VaiTro> role_list, HttpServletRequest httpServletRequest) {
		//Đang chờ phê duyệt
		//Đang chờ giao
		//Đang giao
		//Hoàn thành
		//Đã bị hủy
		//
		
		if(!validation.isLoggin(httpServletRequest)) {
			return "redirect:/login";
		}
		
		String memberAcess=this.validation.manageRoleAccess(role_list,vaiTroService.getVaiTroByName("ROLE_ADMIN"));
		if(!memberAcess.equals("ok")) {
			return memberAcess;
		}
		
		int soLuongDonHangMoi=this.donHangService.soLuongDonHangByTrangThai("Đang chờ giao");
		int soLuongDonHangCanPheDuyet=this.donHangService.soLuongDonHangByTrangThai("Đang chờ phê duyệt");;
		
		model.addAttribute("soLuongDonHangMoi", soLuongDonHangMoi);
		model.addAttribute("soLuongDonHangCanPheDuyet", soLuongDonHangCanPheDuyet);
		
		return "Admin/home";
		
	}
//	@GetMapping("/don-hang1")
//	public String test() {
//		System.out.println("asdfadf");
//		return "Admin/quanLyDonHang";
//	}
	
	@GetMapping("/don-hang")
	public String quanLyDonHang(Model model,
			@RequestParam(name="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name ="trangThaiDonHang", defaultValue = "Tất cả") String trangThaiDonHang,
			@RequestParam(name = "startDate", defaultValue = "") String startDate,
			@RequestParam(name="endDate", defaultValue = "") String endDate,
			@RequestParam(name="donHangID", defaultValue = "0") long donHangID
			) {
		
		
		SearchDonHangCriteria searchDonHangCriteria=new SearchDonHangCriteria();
		searchDonHangCriteria.setTrangThaiDonHang(trangThaiDonHang);
		if(!startDate.equals(""))
			searchDonHangCriteria.setStartDate(Date.valueOf(startDate));
		if(!endDate.equals(""))
			searchDonHangCriteria.setEndDate(Date.valueOf(endDate));
		if(donHangID!=0)
			searchDonHangCriteria.setId(donHangID);
		
		int noAllowedPage=5;//only allow odd number
		int noResultPerPage=8;
		
		List<DonHang> donHangList=this.donHangService.getDonhangByCriteriaPageConstraint(searchDonHangCriteria, (currentPage-1)*noResultPerPage, noResultPerPage);
		model.addAttribute("donHangList",donHangList);
		
		
		int totalResult=this.donHangService.getTotalNumberDonHangByCriteria(searchDonHangCriteria);
		int totalPage=(int)Math.ceil((double)totalResult/noResultPerPage);
		int halfAllowedPage=(int)noAllowedPage/2;
		
		try {
			trangThaiDonHang=java.net.URLDecoder.decode(trangThaiDonHang, StandardCharsets.UTF_8.name());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		List<Integer> pageList=new ArrayList<Integer>();
//		if(currentPage>=1 && currentPage<=5) {
//			for(int i=1; i<=noAllowedPage && i<=totalPage; ++i)
//				pageList.add(i);
//		}
//		else if(currentPage==totalPage) {
//			for(int i=currentPage; i>totalPage-noAllowedPage &&i>0; --i)
//				pageList.add(i);
//		}
//		else {
//			//left side
//			for(int i=1; i<=halfAllowedPage; ++i)
//				pageList.add(currentPage-i);
//			pageList.add(currentPage);
//			//right side
//			for(int i=1; i<=halfAllowedPage; ++i)
//				pageList.add(currentPage+i);
//			
//		}
		
		Pagination pagination=new Pagination(noAllowedPage, noResultPerPage, totalResult, totalPage);
		List<Integer> pageList=pagination.generatePageList(currentPage);
		
		model.addAttribute("pageList", pageList);
		model.addAttribute("trangThaiDonHang",trangThaiDonHang);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		model.addAttribute("donHangID",donHangID);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		String requestParameters="?trangThaiDonHang="+trangThaiDonHang+"&startDate="+startDate+"&endDate="+endDate+"&donHangID="+donHangID;
		model.addAttribute("requestParameters",requestParameters);
		model.addAttribute("url", "/admin/don-hang");
		
		
		
		
		

	
		
		
		
		
		
		return "Admin/quanLyDonHang";
	}
	
	
}
