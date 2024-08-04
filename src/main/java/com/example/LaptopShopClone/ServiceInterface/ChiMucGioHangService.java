package com.example.LaptopShopClone.ServiceInterface;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.SanPham;

public interface ChiMucGioHangService {
	
	ChiMucGioHang getChiMucGioHangBySanPhangGioHang(SanPham sanPham, GioHang gioHang);
	ChiMucGioHang SaveOrUpdate(ChiMucGioHang chiMucGioHang);
	void remove(ChiMucGioHang chiMucGioHang);
	
}
