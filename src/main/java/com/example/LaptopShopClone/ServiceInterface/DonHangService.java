package com.example.LaptopShopClone.ServiceInterface;

import java.util.List;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Utils.SearchDonHangCriteria;

public interface DonHangService {
	DonHang SaveOrUpdate(DonHang donHang);
	
	List<DonHang> getDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit);
	int getSoLuongDonHangByNguoiDung(NguoiDung nguoiDung);
	
	long getOrderTotal(DonHang donHang);
	
	DonHang getDonHangById(long id);
	
	int soLuongDonHangByTrangThai(String trangThai);
	
	int getTotalNumberDonHang();
	
	List<DonHang> getDonhangByCriteriaPageConstraint(SearchDonHangCriteria searchDonHangCriteria, int offset, int limit);
	
	int getTotalNumberDonHangByCriteria(SearchDonHangCriteria searchDonHangCriteria);
	
	
}
