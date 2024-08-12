package com.example.LaptopShopClone.Utils;

import java.sql.Date;
import java.util.List;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;

public class ThongTinChiTietDonHang {
	private String sdtNguoiNhan;
	private String diaChiNhan;
	private String tenNguoiNhan;
	
	private String trangThaiDonHang;
	private Date ngayDat;
	private Date ngayGiaoHang;
	private Date ngayNhanHang;
	private long tongGiaTri;
	
	List<ChiTietDonHang> chiTietDonHangs;
	

	private String tenNguoiDat;
	private long idNguoiDat;
	
	private String tenShipper;
	private long idShipper;
	public String getSdtNguoiNhan() {
		return sdtNguoiNhan;
	}
	public void setSdtNguoiNhan(String sdtNguoiNhan) {
		this.sdtNguoiNhan = sdtNguoiNhan;
	}
	public String getDiaChiNhan() {
		return diaChiNhan;
	}
	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}
	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}
	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}
	public String getTrangThaiDonHang() {
		return trangThaiDonHang;
	}
	public void setTrangThaiDonHang(String trangThaiDonHang) {
		this.trangThaiDonHang = trangThaiDonHang;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public Date getNgayGiaoHang() {
		return ngayGiaoHang;
	}
	public void setNgayGiaoHang(Date ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}
	public Date getNgayNhanHang() {
		return ngayNhanHang;
	}
	public void setNgayNhanHang(Date ngayNhanHang) {
		this.ngayNhanHang = ngayNhanHang;
	}
	public long getTongGiaTri() {
		return tongGiaTri;
	}
	public void setTongGiaTri(long tongGiaTri) {
		this.tongGiaTri = tongGiaTri;
	}
	public List<ChiTietDonHang> getChiTietDonHangs() {
		return chiTietDonHangs;
	}
	public void setChiTietDonHangs(List<ChiTietDonHang> chiTietDonHangs) {
		this.chiTietDonHangs = chiTietDonHangs;
	}
	public String getTenNguoiDat() {
		return tenNguoiDat;
	}
	public void setTenNguoiDat(String tenNguoiDat) {
		this.tenNguoiDat = tenNguoiDat;
	}
	public long getIdNguoiDat() {
		return idNguoiDat;
	}
	public void setIdNguoiDat(long idNguoiDat) {
		this.idNguoiDat = idNguoiDat;
	}
	public String getTenShipper() {
		return tenShipper;
	}
	public void setTenShipper(String tenShipper) {
		this.tenShipper = tenShipper;
	}
	public long getIdShipper() {
		return idShipper;
	}
	public void setIdShipper(long idShipper) {
		this.idShipper = idShipper;
	}
	

	
}
