package com.example.LaptopShopClone.Repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.ChiMucGioHang;
import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Repository
public class ChiMucGioHangRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	public ChiMucGioHang getChiMucGioHangBySanPhangGioHang(SanPham sanPham, GioHang gioHang) {
		ChiMucGioHang chiMucGioHang=null;
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from chimucgiohang where ma_gio_hang=:ma_gio_hang and ma_san_pham=:ma_san_pham", ChiMucGioHang.class);
		long ma_san_pham=sanPham.getId();
		long ma_gio_hang=gioHang.getId();
		sql.setParameter("ma_gio_hang", ma_gio_hang);
		sql.setParameter("ma_san_pham", ma_san_pham);
		chiMucGioHang=(ChiMucGioHang)sql.uniqueResult();
		
	
		System.out.println("here1");
		
		return chiMucGioHang;
	}
	
	public ChiMucGioHang save(ChiMucGioHang chiMucGioHang) {
		ChiMucGioHang chiMucGioHang2=this.getChiMucGioHangBySanPhangGioHang(chiMucGioHang.getSanPham(), chiMucGioHang.getGioHang());
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		
		if(chiMucGioHang2!=null) {
			chiMucGioHang.setId(chiMucGioHang2.getId());
			session.merge(chiMucGioHang);
			
		}
		else {
			session.persist(chiMucGioHang);
		}
		transaction.commit();
		
		return chiMucGioHang;
		
	}
	public void remove(ChiMucGioHang chiMucGioHang) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		session.remove(chiMucGioHang);
		transaction.commit();
	}
	
}
