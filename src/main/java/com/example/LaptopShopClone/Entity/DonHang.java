package com.example.LaptopShopClone.Entity;

import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

//class ChiTietDonHangListSerializer extends JsonSerializer<List<ChiTietDonHang>>{
//	
//	@Override
//	public void serialize(List<ChiTietDonHang> value, JsonGenerator jgen, SerializerProvider serializerProvider) {
//		try {
//			jgen.writeStartObject();
//			jgen.writeArrayFieldStart("ChiTietDonHangList");
//			for(ChiTietDonHang ctdh: value) {
//				jgen.writeObject(ctdh.getId());
//			}
//			jgen.writeEndObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}

class NguoiDatSerialzer extends JsonSerializer<NguoiDung>{

	
	
	@Override
	public void serialize(NguoiDung value, JsonGenerator jgen, SerializerProvider serializerProvider) {
		
		if(value==null) {
			try {
				jgen.writeStartObject();
				jgen.writeNull();
				jgen.writeEndObject();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return;
		}
		
		try {
			jgen.writeStartObject();
			jgen.writeNumberField("id", value.getId());;
			jgen.writeStringField("email", value.getEmail());
			jgen.writeStringField("hoTen", value.getHoTen());
			jgen.writeStringField("soDienThoai", value.getSoDienThoai());
			jgen.writeStringField("diaChi", value.getDiaChi());
			jgen.writeEndObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}

class ShipperSerialzer extends JsonSerializer<NguoiDung>{
	@Override
	public void serialize(NguoiDung value, JsonGenerator jgen, SerializerProvider serializerProvider) {
		if(value==null) {
			try {
				jgen.writeStartObject();
				jgen.writeNull();
				jgen.writeEndObject();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return;
		}
		
		try {
			jgen.writeStartObject();
			jgen.writeNumberField("id", value.getId());;
			jgen.writeStringField("email", value.getEmail());
			jgen.writeStringField("hoTen", value.getHoTen());
			jgen.writeStringField("soDienThoai", value.getSoDienThoai());
			jgen.writeStringField("diaChi", value.getDiaChi());
			jgen.writeEndObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}

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
	
//	@OneToOne
//	@JoinColumn(name="trangThaiDonHang")
//	TrangThaiDonHang trangThaiDonHang;
	
	@ManyToOne
	@JoinColumn(name ="ma_nguoi_dat")
	@JsonSerialize(using=NguoiDatSerialzer.class)
	private NguoiDung nguoiDat;
	
	
	@ManyToOne
	@JoinColumn(name="ma_shipper")
	@JsonSerialize(using = ShipperSerialzer.class)
//	@JsonIgnore
	private NguoiDung shipper;
	
	@OneToMany(mappedBy = "donHang")
//	@JsonIgnore
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
//	
//	public TrangThaiDonHang getTrangThaiDonHang() {
//		return trangThaiDonHang;
//	}
//	public void setTrangThaiDonHang(TrangThaiDonHang trangThaiDonHang) {
//		this.trangThaiDonHang = trangThaiDonHang;
//	}
	
	
	public String getGhiChu() {
		return ghiChu;
	}
	public String getTrangThaiDonHang() {
		return trangThaiDonHang;
	}
	public void setTrangThaiDonHang(String trangThaiDonHang) {
		this.trangThaiDonHang = trangThaiDonHang;
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
