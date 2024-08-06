package com.example.LaptopShopClone.ServiceInterface;

import java.util.List;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;

public interface ChiTietDonHangService {
	ChiTietDonHang SaveOrUpdate(ChiTietDonHang chiTietDonHang);
	List<ChiTietDonHang> getChiTietDonHangFromDonHang(DonHang donHang);

}

