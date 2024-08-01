package com.example.LaptopShopClone.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryUtil {
		SessionFactory sessionFactory;
		
		public Boolean buildSessionFactory() {
			if(sessionFactory!=null) {
				sessionFactory.close();
			}
			try {
				this.sessionFactory=new Configuration().configure().buildSessionFactory();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if(this.sessionFactory!=null)
				return true;
			else {
				return false;
			}
			
		}
		
		public SessionFactoryUtil() {
			System.out.println("SessionFactoryUtil is called");
			try {
				this.sessionFactory=new Configuration().configure().buildSessionFactory();
				System.out.println("SessionFactoryUtil is created");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
		
		
		
}
