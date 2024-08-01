package com.example.LaptopShopClone.Entity;

import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DonHang {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	private String diaChiNhan;
	private String sdtNhanHang;
	private String hoTenNguoiNhan;
	private Date ngayDatHang;
	private Date ngayGiaoHang;
	private Date ngayNhanHang;
	private String trangThaiDonHang;
	private String ghiChu;
	private long tongGiaTri;
	
	@ManyToOne
	@JoinColumn(name ="ma_nguoi_dat")
	private NguoiDung nguoiDat;
	
	@ManyToOne
	@JoinColumn(name="ma_shipper")
	private NguoiDung shipper;
	
	@OneToMany(mappedBy = "donHang")
	private List<ChiTietDonHang> danhSachChiTiet;
	
	public DonHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDiaChiNhan() {
		return diaChiNhan;
	}
	public void setDiaChiNhan(String diaChiNhan) {
		this.diaChiNhan = diaChiNhan;
	}
	public String getSdtNhanHang() {
		return sdtNhanHang;
	}
	public void setSdtNhanHang(String sdtNhanHang) {
		this.sdtNhanHang = sdtNhanHang;
	}
	public String getHoTenNguoiNhan() {
		return hoTenNguoiNhan;
	}
	public void setHoTenNguoiNhan(String hoTenNguoiNhan) {
		this.hoTenNguoiNhan = hoTenNguoiNhan;
	}
	public Date getNgayDatHang() {
		return ngayDatHang;
	}
	public void setNgayDatHang(Date ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
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
	public String getTrangThaiDonHang() {
		return trangThaiDonHang;
	}
	public void setTrangThaiDonHang(String trangThaiDonHang) {
		this.trangThaiDonHang = trangThaiDonHang;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public long getTongGiaTri() {
		return tongGiaTri;
	}
	public void setTongGiaTri(long tongGiaTri) {
		this.tongGiaTri = tongGiaTri;
	}
	public NguoiDung getNguoiDat() {
		return nguoiDat;
	}
	public void setNguoiDat(NguoiDung nguoiDat) {
		this.nguoiDat = nguoiDat;
	}
	public NguoiDung getShipper() {
		return shipper;
	}
	public void setShipper(NguoiDung shipper) {
		this.shipper = shipper;
	}
	public List<ChiTietDonHang> getDanhSachChiTiet() {
		return danhSachChiTiet;
	}
	public void setDanhSachChiTiet(List<ChiTietDonHang> danhSachChiTiet) {
		this.danhSachChiTiet = danhSachChiTiet;
	}
	
	
	
}
