package com.example.LaptopShopClone.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.VaiTro;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Repository
public class VaiTroRepository {
	SessionFactoryUtil sessionFactoryUtil;
	
	@Autowired
	public VaiTroRepository(SessionFactoryUtil sessionFactoryUtil) {
		this.sessionFactoryUtil=sessionFactoryUtil;
	}
	
	public VaiTro getVaiTroByName(String name) {
		SessionFactory sessionFactory=this.sessionFactoryUtil.getSessionFactory();
		
		VaiTro vaiTro=null;
		if(sessionFactory!=null) {
			Session session=sessionFactory.openSession();
			Query query=session.createNativeQuery("select * from vaitro where tenVaiTro=:tenVaiTro;", VaiTro.class);
			query.setParameter("tenVaiTro", name);
			vaiTro=(VaiTro)query.uniqueResult();
			
			
		}
		else {
			System.out.println(this.getClass().getName()+":"+"session factory is null");
			return null;
		}
		return vaiTro;
		
	}
	
	//addVaitro
	//updateVaitro
	
	
}
