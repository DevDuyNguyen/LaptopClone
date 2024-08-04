package com.example.LaptopShopClone.Entity;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class GioHang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String tong_tien;
	
	@OneToOne
	@JoinColumn(name = "ma_nguoi_dung")
	private NguoiDung nguoiDung;
	
	@OneToMany(mappedBy ="gioHang")
	private List<ChiMucGioHang> chiMucGioHangs;

	public GioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTong_tien() {
		return tong_tien;
	}

	public void setTong_tien(String tong_tien) {
		this.tong_tien = tong_tien;
	}

	public NguoiDung getNguoiDung() {
		return nguoiDung;
	}

	public void setNguoiDung(NguoiDung nguoiDung) {
		this.nguoiDung = nguoiDung;
	}

	public List<ChiMucGioHang> getChiMucGioHangs() {
		return chiMucGioHangs;
	}

	public void setChiMucGioHangs(List<ChiMucGioHang> chiMucGioHangs) {
		this.chiMucGioHangs = chiMucGioHangs;
	}

	@Override
	public String toString() {
		return "GioHang [id=" + id + ", tong_tien=" + tong_tien + ", nguoiDung=" + nguoiDung + ", chiMucGioHangs="
				+ chiMucGioHangs + "]";
	}

	
	
	
	
}
