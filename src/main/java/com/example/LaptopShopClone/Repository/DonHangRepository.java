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
	
	public List<ChiTietDonHang> getDonHangByNguoiDung(NguoiDung nguoiDung, int offset, int limit){
		List<ChiTietDonHang> chiTietDonHangs=null;
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("Select * from donhang where ma_nguoi_dat=:ma_nguoi_dat LIMIT :limit OFFSET :offset;", ChiTietDonHang.class);
		
		sql.setParameter("ma_nguoi_dat", nguoiDung.getId());
		sql.setParameter("limit", limit);
		sql.setParameter("offset", offset);	
		
		chiTietDonHangs=sql.getResultList();
		
		return chiTietDonHangs;
	}
	
}
