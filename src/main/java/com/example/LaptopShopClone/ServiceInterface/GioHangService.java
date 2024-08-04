package com.example.LaptopShopClone.ServiceInterface;

import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;

public interface GioHangService {
	GioHang getGioHangByNguoiDung(NguoiDung nguoiDung);
	void addGioHangForNguoiDung(NguoiDung nguoiDung);
	void updateGiohang(GioHang gioHang);
	void saveOrUpdate(GioHang gioHang);
	
}
