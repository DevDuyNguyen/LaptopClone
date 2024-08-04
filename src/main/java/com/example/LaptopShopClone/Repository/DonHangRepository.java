package com.example.LaptopShopClone.Repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.DonHang;
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
}
