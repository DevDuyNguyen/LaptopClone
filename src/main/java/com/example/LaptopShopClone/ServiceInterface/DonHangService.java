package com.example.LaptopShopClone.ServiceInterface;

import java.util.List;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;

public interface DonHangService {
	DonHang SaveOrUpdate(DonHang donHang);
	
	int getSoLuongDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit);
	
	List<ChiTietDonHang> getDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit);
	
	long getOrderTotal(DonHang donHang);
}
