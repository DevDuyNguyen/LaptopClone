package com.example.LaptopShopClone.Repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.TrangThaiDonHang;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;



@Repository
public class TrangThaiDonHangRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	public TrangThaiDonHang getTrangThaiDonHangByName(String name) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from trangthaidonhang where tenTrangThai=:tenTrangThai", TrangThaiDonHang.class);
		return (TrangThaiDonHang)sql.uniqueResult();
	}
	
	
}
