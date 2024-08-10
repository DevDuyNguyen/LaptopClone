package com.example.LaptopShopClone.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.ChiTietDonHang;
import com.example.LaptopShopClone.Entity.DonHang;
import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Repository
public class DonHangRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	public DonHang saveOrUpdate(DonHang donHang) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		DonHang donHang2=session.get(DonHang.class, donHang.getId());
		Transaction transaction=session.beginTransaction();
		if(donHang2==null){//don hang chua ton tai
			
			session.persist(donHang);
			
		}
		else {
			session.merge(donHang);
		}
		transaction.commit();
			
		return donHang;
	}
	
	public List<DonHang> getDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit){
		List<DonHang> donHangs=null;
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("Select * from donhang where ma_nguoi_dat=:ma_nguoi_dat LIMIT :limit OFFSET :offset;", DonHang.class);
		
		sql.setParameter("ma_nguoi_dat", nguoiDung.getId());
		sql.setParameter("limit", limit);
		sql.setParameter("offset", offset);	
		
		donHangs=sql.getResultList();
		
		return donHangs;
	}
	
	public List<DonHang> getAllDonHangByNguoiDung(NguoiDung nguoiDung){
		List<DonHang> chiTietDonHangs=null;
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("Select * from donhang where ma_nguoi_dat=:ma_nguoi_dat", DonHang.class);
		
		sql.setParameter("ma_nguoi_dat", nguoiDung.getId());
		chiTietDonHangs=sql.getResultList();
		
		return chiTietDonHangs;
	}
	
	public DonHang getDonHangByID(long id) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from donhang where id=:id", DonHang.class);
		
		sql.setParameter("id", id);
		
		return (DonHang)sql.uniqueResult();
		
	}
	
	public int soLuongDonHangByTrangThai(String trangThai) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select count(*) from donhang where trangThaiDonHang=:trangThaiDonHang", DonHang.class);
		sql.setParameter("trangThaiDonHang", trangThai);
		
		return (Integer)sql.uniqueResult();
		
	}
	
}
