package com.example.LaptopShopClone.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.GioHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.ServiceInterface.NguoiDungService;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;


@Repository
public class gioHangRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	@Autowired 
	NguoiDungService nguoiDungService;
	
	public GioHang getGioHangByNguoiDung(NguoiDung nguoiDung) {
		SessionFactory sessionFactory=this.sessionFactoryUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Query sql=session.createNativeQuery("select * from giohang where ma_nguoi_dung=:ma_nguoi_dung", GioHang.class);
		long ma_nguoi_dung=nguoiDung.getId();
		sql.setParameter("ma_nguoi_dung", ma_nguoi_dung);
		
		GioHang gioHang=(GioHang)sql.uniqueResult();
		
		
		return gioHang;
		
	}
	
	public void save(GioHang gioHang) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		
		NguoiDung nguoiDung=gioHang.getNguoiDung();
		GioHang gioHangCu=this.getGioHangByNguoiDung(nguoiDung);
		Transaction transaction=session.beginTransaction();
		if(gioHangCu!=null) {
			gioHang.setId(gioHangCu.getId());
			session.merge(gioHang);
			System.out.println("update old one");
		}
		else {
			System.out.println("save new one");
			session.persist(gioHang);
		}
		transaction.commit();
		
	}
	
	
}
