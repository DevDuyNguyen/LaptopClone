package com.example.LaptopShopClone.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.HangSanXuat;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;
import com.mysql.cj.xdevapi.SessionFactory;

@Repository
public class HangSanXuatRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	public List<HangSanXuat> getAllHSX(){
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select * from hangsanxuat;", HangSanXuat.class);
		return sql.getResultList();
	}
	
	
}
