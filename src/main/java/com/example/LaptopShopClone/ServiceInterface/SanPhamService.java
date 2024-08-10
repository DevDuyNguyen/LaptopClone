package com.example.LaptopShopClone.ServiceInterface;

import java.util.List;
import java.util.Set;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Utils.SearchSanPhamCriteria;

public interface SanPhamService {
	List<SanPham> searchSanPham(SearchSanPhamCriteria searchSanPhamCriteria,int page, int resultPerPage);
	int getTotalResultCount(SearchSanPhamCriteria searchSanPhamCriteria);
	SanPham getSanPhamByID(long id);
	Set<DanhMuc> getAllDanhMucFromsearchSanPham(SearchSanPhamCriteria searchSanPhamCriteria);
	Set<HangSanXuat> getAllHSXFromsearchSanPham(SearchSanPhamCriteria searchSanPhamCriteria);
}
