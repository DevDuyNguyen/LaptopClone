package com.example.LaptopShopClone.ServiceInterface;

import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;

public interface GioHangService {
	GioHang getGioHangByNguoiDung(NguoiDung nguoiDung);
	GioHang addGioHangForNguoiDung(NguoiDung nguoiDung);
	void updateGiohang(GioHang gioHang);
	GioHang saveOrUpdate(GioHang gioHang);
	
}
