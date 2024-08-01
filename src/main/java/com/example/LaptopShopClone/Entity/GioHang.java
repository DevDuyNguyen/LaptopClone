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

	public GioHang(long id, String tong_tien) {
		super();
		this.id = id;
		this.tong_tien = tong_tien;
	}

	@Override
	public String toString() {
		return "GioHang [id=" + id + ", tong_tien=" + tong_tien + "]";
	}
	
	
	
}
