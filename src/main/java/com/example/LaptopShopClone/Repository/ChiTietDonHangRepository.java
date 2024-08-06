package com.example.LaptopShopClone.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Repository
public class ChiTietDonHangRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	public ChiTietDonHang getChiTietDonHangFromDonHangSanPham(DonHang donHang, SanPham sanPham) {
		ChiTietDonHang chiTietDonHang=null;
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from chitietdonhang where ma_don_hang=:ma_don_hang AND ma_san_pham=:ma_san_pham;", ChiTietDonHang.class);
		sql.setParameter("ma_don_hang", donHang.getId());
		sql.setParameter("ma_san_pham", sanPham.getId());
		chiTietDonHang=(ChiTietDonHang)sql.uniqueResult();
		
		return chiTietDonHang;
	}
	
	public ChiTietDonHang SaveOrUpdate(ChiTietDonHang chiTietDonHang) {
		ChiTietDonHang chiTietDonHang2=this.getChiTietDonHangFromDonHangSanPham(chiTietDonHang.getDonHang(), chiTietDonHang.getSanPham());
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		if(chiTietDonHang2==null) {
			session.persist(chiTietDonHang);
		}
		else {
			chiTietDonHang.setId(chiTietDonHang2.getId());
			session.merge(chiTietDonHang);
		}
		
		transaction.commit();
		
		return chiTietDonHang;
		
	}
	
	public List<ChiTietDonHang> getChiTietDonHangFromDonHang(DonHang donHang) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from chitietdonhang where ma_don_hang=:ma_don_hang", DonHang.class);
		sql.setParameter("ma_don_hang", donHang.getId());
		
		return sql.getResultList();
		
	}
	
	
	
}
