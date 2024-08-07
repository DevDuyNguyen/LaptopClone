package com.example.LaptopShopClone.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.NguoiDung;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class NguoiDungRepository {
	private HikariDataSource ds;
	private SessionFactoryUtil sessionFactoryUtil;
	
//	@Autowired
//	public NguoiDungRepository(HikariDataSource ds) throws Exception{
//		this.ds=ds;
//	}
	
	@Autowired
	public NguoiDungRepository(SessionFactoryUtil sessionFactoryUtil) {
		this.sessionFactoryUtil=sessionFactoryUtil;
	}
	
	public NguoiDung getNguoiDungByEmail(String email) {
		SessionFactory sessionFactory=this.sessionFactoryUtil.getSessionFactory();
		NguoiDung nguoiDung=null;
		if(sessionFactory!=null) {
			Session session=sessionFactory.openSession();
			Query query=session.createNativeQuery("select * from nguoidung where email=:email;", NguoiDung.class);
			query.setParameter("email", email);
			nguoiDung=(NguoiDung)query.uniqueResult();
			
		}
		else {
			System.out.println(this.getClass().getName()+":"+"session factory is null");
			return null;
		}
		return nguoiDung;
		
	}
	
	
	public NguoiDung addNguoiDung(NguoiDung nguoiDung) {
		SessionFactory sessionFactory=sessionFactoryUtil.getSessionFactory();
		
		if(sessionFactory!=null) {
			Session session=sessionFactory.openSession();
			Transaction transaction=session.beginTransaction();
			session.persist(nguoiDung);
			transaction.commit();
			
			
		}
		else {
			System.out.println(this.getClass().getName()+":"+"session factory is null");
			return null;
		}
		return nguoiDung;
		
	}
	
	public NguoiDung SaveOrUpdate(NguoiDung nguoiDung) {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		NguoiDung nguoiDung2=this.getNguoiDungByEmail(nguoiDung.getEmail());
		if(nguoiDung2==null) {
			session.persist(nguoiDung);
		}
		else {
			nguoiDung.setId(nguoiDung2.getId());
			session.merge(nguoiDung);
		}
		
		transaction.commit();
		
		return nguoiDung;
	}
	

	
}
