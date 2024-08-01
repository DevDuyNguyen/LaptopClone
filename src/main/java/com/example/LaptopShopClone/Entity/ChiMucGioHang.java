package com.example.LaptopShopClone.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChiMucGioHang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "ma_san_pham")
	private SanPham sanPham;
	
	private int so_luong;
	
	@ManyToOne
	@JoinColumn(name = "ma_gio_hang")
	private GioHang gioHang;

	public ChiMucGioHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSo_luong() {
		return so_luong;
	}

	public void setSo_luong(int so_luong) {
		this.so_luong = so_luong;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}

	@Override
	public String toString() {
		return "ChiMucGioHang [id=" + id + ", so_luong=" + so_luong + "]";
	}
}
