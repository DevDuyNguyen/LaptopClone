package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
