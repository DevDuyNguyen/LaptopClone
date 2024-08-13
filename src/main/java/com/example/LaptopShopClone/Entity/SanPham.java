package com.example.LaptopShopClone.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

class SanPhamDanhMucSerialzer extends JsonSerializer<DanhMuc>{
	@Override
	public void serialize(DanhMuc value, JsonGenerator jgen, SerializerProvider serializerProvider) {
		try {
			jgen.writeStartObject();
			jgen.writeStringField("danhmuc", value.getTenDanhMuc());
			jgen.writeEndObject();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class SanPhamHangSanXuatSerialzer extends JsonSerializer<HangSanXuat>{
	@Override
	public void serialize(HangSanXuat value, JsonGenerator jgen, SerializerProvider serializerProvider) {
		try {
			jgen.writeStartObject();
			jgen.writeStringField("danhmuc", value.getTenHangSanXuat());
			jgen.writeEndObject();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

@Entity
public class SanPham {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String tenSanPham;
	private long donGia;
	private int donViKho;
	private int donViBan;
	private String thongTinBaoHanh;
	private String thongTinChung;
	private String manHinh;
	private String heDieuHanh;
	private String cpu;
	private String ram;
	private String thietKe;
	private String dungLuongPin;
	
//	private MultipartFile hinhAnh; Cach xu ly anh

	@ManyToOne
	@JoinColumn(name = "ma_danh_muc")
	@JsonSerialize(using=SanPhamDanhMucSerialzer.class)
	private DanhMuc danhMuc;

	@ManyToOne
	@JoinColumn(name = "ma_hang_sx")
	@JsonSerialize(using=SanPhamHangSanXuatSerialzer.class)
	private HangSanXuat hangSanXuat;
	
	

	public SanPham() {
		super();
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

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public int getDonViKho() {
		return donViKho;
	}

	public void setDonViKho(int donViKho) {
		this.donViKho = donViKho;
	}

	public int getDonViBan() {
		return donViBan;
	}

	public void setDonViBan(int donViBan) {
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

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	public HangSanXuat getHangSanXuat() {
		return hangSanXuat;
	}

	public void setHangSanXuat(HangSanXuat hangSanXuat) {
		this.hangSanXuat = hangSanXuat;
	}

	@Override
	public String toString() {
		return "SanPham [id=" + id + ", tenSanPham=" + tenSanPham + ", donGia=" + donGia + ", donViKho=" + donViKho
				+ ", donViBan=" + donViBan + ", thongTinBaoHanh=" + thongTinBaoHanh + ", thongTinChung=" + thongTinChung
				+ ", manHinh=" + manHinh + ", heDieuHanh=" + heDieuHanh + ", cpu=" + cpu + ", ram=" + ram + ", thietKe="
				+ thietKe + ", dungLuongPin=" + dungLuongPin + "]";
	}

	
	
	
	
	
}
