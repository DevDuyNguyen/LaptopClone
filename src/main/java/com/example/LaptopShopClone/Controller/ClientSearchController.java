package com.example.LaptopShopClone.Controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceImpl.SanPhamServiceImpl;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.SearchSanPhamCriteria;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("loggedUser")
public class ClientSearchController {
	@Autowired 
	SanPhamServiceImpl sanPhamServiceImpl;
	
	@ModelAttribute("loggedUser")
	public NguoiDung getloggedUser(HttpServletRequest httpServletRequest) {
		return (NguoiDung)httpServletRequest.getSession().getAttribute("loggedUser");
	}
	
	
	@GetMapping("/simpleSearch")
	//perspective: nhin nhan search critera nhu la mot doi tuong trong oop
	public String simpleSearch(@RequestParam("tenSanPham") String tenSanPham,
			@RequestParam(name = "sort", defaultValue = "") String sort,
			@RequestParam(name = "minValue", defaultValue = "0") int minValue,
			@RequestParam(name = "maxValue", defaultValue = "-1") long maxValue,
			@RequestParam(name = "danhMuc", defaultValue = "") String danhMuc,
			@RequestParam(name="hsx", defaultValue = "") String hsx,
			@RequestParam(name = "page", defaultValue = "1") int page,
			Model model
			) {
		
		SearchSanPhamCriteria searchSanPhamCriteria=new SearchSanPhamCriteria();
		searchSanPhamCriteria.setKeyWord(tenSanPham.split(" "));
		searchSanPhamCriteria.setSort(sort);
		searchSanPhamCriteria.setMinValue(minValue);
		searchSanPhamCriteria.setMaxValue(maxValue);
		searchSanPhamCriteria.setDanhMuc(danhMuc);
		searchSanPhamCriteria.setHsx(hsx);
		
		
		int resultPerPage=4;
		int PaginationPerPage=4;
		List<SanPham> resultList=sanPhamServiceImpl.searchSanPham(searchSanPhamCriteria, page, resultPerPage);
		List<Integer> pageList=new ArrayList<Integer>();
		int totalCount=this.sanPhamServiceImpl.getTotalResultCount(searchSanPhamCriteria);
		int totalPage=(int) Math.ceil((double)totalCount/resultPerPage);//luon luon can than auto casting
		
		Set<DanhMuc> danhMucList=new HashSet<DanhMuc>();
		Set<HangSanXuat> hangSanXuatList=new HashSet<HangSanXuat>();
		
		for(SanPham sp:resultList) {
			danhMucList.add(sp.getDanhMuc());
			hangSanXuatList.add(sp.getHangSanXuat());
			System.out.println(sp);
		}

		
		if(page==1||page==2||page==3||page==4) {
			for(int i=1; i<=totalPage && i<=4; ++i) {
				pageList.add(i);
			}
		}
		else if(page!=totalPage) {
			//left pages
			for(int i=page-1; i>=page-(int)(PaginationPerPage/2)&&i>=1; --i) {
				pageList.add(i);
			}
			pageList.add(page);
			//right pages
			for(int i=page+1; i<=page+(int)(PaginationPerPage/2)&&i<=totalPage; ++i) {
				pageList.add(i);
			}
		}
		else if(page==totalPage) {
			for(int i=totalPage-PaginationPerPage+1; i<=totalPage; ++i) {
				pageList.add(i);
			}
		}
		
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("pageList", pageList);
		
		model.addAttribute("sort", sort);
		model.addAttribute("minValue", minValue);
		model.addAttribute("maxValue", maxValue);
		model.addAttribute("danhMuc", danhMuc);
		model.addAttribute("hsx", hsx);
		model.addAttribute("page", page);
		model.addAttribute("tenSanPham", tenSanPham);
		model.addAttribute("routing", "/simpleSearch");
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("danhMucList", danhMucList);
		model.addAttribute("hangSanXuatList", hangSanXuatList);
		
		System.out.println("page:"+page);
		
		if(resultList.size()>0)
			model.addAttribute("hasResult", true);
		else
			model.addAttribute("hasResult", false);
		
		return "/Client/searchResult";
	}
	
	
}
