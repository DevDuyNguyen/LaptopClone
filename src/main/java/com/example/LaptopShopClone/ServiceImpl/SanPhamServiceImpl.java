package com.example.LaptopShopClone.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Repository.SanPhamRepository;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.SearchSanPhamCriteria;

@Service
public class SanPhamServiceImpl implements SanPhamService{
	@Autowired
	SanPhamRepository sanPhamRepository;
	
	public List<SanPham> searchSanPham(SearchSanPhamCriteria searchSanPhamCriteria, int page, int resultPerPage){
		List<SanPham> resuList=sanPhamRepository.searchSanPhamByCriteriaConstraint(searchSanPhamCriteria, resultPerPage, (page-1)*resultPerPage+1);
		return resuList;
	}
	
	public int getTotalResultCount(SearchSanPhamCriteria searchSanPhamCriteria) {
		List<SanPham> resultList=sanPhamRepository.searchSanPhamByCriteria(searchSanPhamCriteria);
		
		
		return resultList.size();
	}
	
	public SanPham getSanPhamByID(long id) {
		return this.sanPhamRepository.getSanPhamByID(id);
	}
	
	public Set<DanhMuc> getAllDanhMucFromsearchSanPham(SearchSanPhamCriteria searchSanPhamCriteria){
		Set<DanhMuc> danhMucs=new HashSet<DanhMuc>();
		List<SanPham> sanPhams=sanPhamRepository.searchSanPhamByCriteria(searchSanPhamCriteria);
		
		for(SanPham sp: sanPhams) {
			danhMucs.add(sp.getDanhMuc());
		}
		
		return danhMucs;
	}
	
	public Set<HangSanXuat> getAllHSXFromsearchSanPham(SearchSanPhamCriteria searchSanPhamCriteria){
		Set<HangSanXuat> hangSanXuats=new HashSet<HangSanXuat>();
		List<SanPham> sanPhams=sanPhamRepository.searchSanPhamByCriteria(searchSanPhamCriteria);
		
		for(SanPham sp: sanPhams) {
			hangSanXuats.add(sp.getHangSanXuat());
		}
		
		return hangSanXuats;
	}
	
	
}
