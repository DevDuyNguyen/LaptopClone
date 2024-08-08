package com.example.LaptopShopClone.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.DanhMuc;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Repository
public class DanhMucRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	public List<DanhMuc> getAllDanhMuc(){
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from danhmuc;", DanhMuc.class);
		
		List<DanhMuc> result=sql.getResultList();
		
		
		return result;
		
	}
	
	
	
}
