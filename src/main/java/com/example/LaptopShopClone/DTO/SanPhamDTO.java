package com.example.LaptopShopClone.DTO;

import org.springframework.web.multipart.MultipartFile;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Entity.SanPham;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SanPhamDTO {
	private long id;

	private String tenSanPham;
	private String donGia;
	private String donViKho;
	private String donViBan;
	private String thongTinBaoHanh;
	private String thongTinChung;
	private String manHinh;
	private String heDieuHanh;
	private String cpu;
	private String ram;
	private String thietKe;
	private String dungLuongPin;
	private MultipartFile hinhAnh;
	private String danhMuc;
	private String hangSanXuat;
	
	public void convertToEntity(SanPham sanPham) {
		sanPham.setTenSanPham(this.tenSanPham);
		sanPham.setDonGia(Long.parseLong(this.donGia));
		sanPham.setDonViKho(Integer.parseInt(this.donViKho));
		sanPham.setThongTinBaoHanh(this.thongTinBaoHanh);
		sanPham.setThongTinChung(this.thongTinChung);
		sanPham.setManHinh(this.manHinh);
		sanPham.setHeDieuHanh(this.heDieuHanh);
		sanPham.setCpu(this.cpu);
		sanPham.setRam(this.ram);
		sanPham.setThietKe(this.thietKe);
		sanPham.setDungLuongPin(this.dungLuongPin);
		
	}
	
	
	public String getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}
	public String getHangSanXuat() {
		return hangSanXuat;
	}
	public void setHangSanXuat(String hangSanXuat) {
		this.hangSanXuat = hangSanXuat;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getDonGia() {
		return donGia;
	}
	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
	public String getDonViKho() {
		return donViKho;
	}
	public void setDonViKho(String donViKho) {
		this.donViKho = donViKho;
	}
	public String getDonViBan() {
		return donViBan;
	}
	public void setDonViBan(String donViBan) {
		this.donViBan = donViBan;
	}
	public String getThongTinBaoHanh() {
		return thongTinBaoHanh;
	}
	public void setThongTinBaoHanh(String thongTinBaoHanh) {
		this.thongTinBaoHanh = thongTinBaoHanh;
	}
	public String getThongTinChung() {
		return thongTinChung;
	}
	public void setThongTinChung(String thongTinChung) {
		this.thongTinChung = thongTinChung;
	}
	public String getManHinh() {
		return manHinh;
	}
	public void setManHinh(String manHinh) {
		this.manHinh = manHinh;
	}
	public String getHeDieuHanh() {
		return heDieuHanh;
	}
	public void setHeDieuHanh(String heDieuHanh) {
		this.heDieuHanh = heDieuHanh;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getThietKe() {
		return thietKe;
	}
	public void setThietKe(String thietKe) {
		this.thietKe = thietKe;
	}
	public String getDungLuongPin() {
		return dungLuongPin;
	}
	public void setDungLuongPin(String dungLuongPin) {
		this.dungLuongPin = dungLuongPin;
	}
	public MultipartFile getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(MultipartFile hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
	
	
}
