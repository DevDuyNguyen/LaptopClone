package com.example.LaptopShopClone.ServiceImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Repository.DonHangRepository;
import com.example.LaptopShopClone.ServiceInterface.ChiTietDonHangService;
import com.example.LaptopShopClone.ServiceInterface.DonHangService;
import com.example.LaptopShopClone.Utils.SearchDonHangCriteria;

@Service
public class DonHangServiceImpl implements DonHangService{
	@Autowired 
	DonHangRepository donHangRepository;
	@Autowired
	ChiTietDonHangService chiTietDonHangService;
	
	public DonHang SaveOrUpdate(DonHang donHang) {
		return this.donHangRepository.saveOrUpdate(donHang);
	}
	
	public List<DonHang> getDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit){
		return this.donHangRepository.getDonHangByNguoiDung(nguoiDung, offset, limit);
	}
	
	public int getSoLuongDonHangByNguoiDung(NguoiDung nguoiDung) {
		List<DonHang> donHangs=this.donHangRepository.getAllDonHangByNguoiDung(nguoiDung);
		return donHangs.size();
	}
	
	public long getOrderTotal(DonHang donHang) {
		long result=0;
		List<ChiTietDonHang> chiTietDonHangs=this.chiTietDonHangService.getChiTietDonHangFromDonHang(donHang);
		for(ChiTietDonHang chiTietDonHang:chiTietDonHangs) {
			result+=chiTietDonHang.getDonGia();
		}
		
		return result;
	}
	
	public DonHang getDonHangById(long id) {
		return this.donHangRepository.getDonHangByID(id);
	}
	
	public int soLuongDonHangByTrangThai(String trangThai) {
		return this.donHangRepository.soLuongDonHangByTrangThai(trangThai);
	}
	
	public 	int getTotalNumberDonHang() {
		return this.donHangRepository.getTotalNumberDonHang();
	}
	
	public List<DonHang> getDonhangByCriteriaPageConstraint(SearchDonHangCriteria searchDonHangCriteria, int offset, int limit){
		return this.donHangRepository.getDonhangByCriteriaPageConstraint(searchDonHangCriteria, offset, limit);
	}
	
}
