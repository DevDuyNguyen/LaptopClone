package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Repository.DonHangRepository;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;

@Service
public class DonHangServiceImpl implements DonHangService{
	@Autowired 
	DonHangRepository donHangRepository;
	@Autowired
	ChiTietDonHangService chiTietDonHangService;
	
	public DonHang SaveOrUpdate(DonHang donHang) {
		return this.donHangRepository.saveOrUpdate(donHang);
	}
	
	public List<ChiTietDonHang> getDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit){
		return this.donHangRepository.getDonHangByNguoiDung(nguoiDung, offset, limit);
	}
	
	public int getSoLuongDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit) {
		List<ChiTietDonHang> chiTietDonHangs=this.getDonHangByNguoiDung(nguoiDung, offset, limit);
		return chiTietDonHangs.size();
	}
	
	public long getOrderTotal(DonHang donHang) {
		long result=0;
		List<ChiTietDonHang> chiTietDonHangs=this.chiTietDonHangService.getChiTietDonHangFromDonHang(donHang);
		for(ChiTietDonHang chiTietDonHang:chiTietDonHangs) {
			result+=chiTietDonHang.getDonGia();
		}
		
		return result;
	}
}
