package com.example.LaptopShopClone.Repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.LaptopShopClone.Entity.SanPham;
import com.example.LaptopShopClone.ServiceInterface.SanPhamService;
import com.example.LaptopShopClone.Utils.SearchSanPhamCriteria;
import com.example.LaptopShopClone.Utils.SessionFactoryUtil;

@Repository
public class SanPhamRepository {
	@Autowired
	SessionFactoryUtil sessionFactoryUtil;
	
	String createSqlCriteria(SearchSanPhamCriteria searchSanPhamCriteria) {
		String sql="SELECT sp.* FROM sanpham as sp"
				+" INNER JOIN hangsanxuat on sp.ma_hang_sx=hangsanxuat.id"
				+" INNER JOIN danhmuc on sp.ma_danh_muc=danhmuc.id"
				+" WHERE ";

		String keyword[]=searchSanPhamCriteria.getKeyWord();
		int minValue=searchSanPhamCriteria.getMinValue();
		long maxValue=searchSanPhamCriteria.getMaxValue();
		long danhMuc=searchSanPhamCriteria.getDanhMuc();
		long hsx=searchSanPhamCriteria.getHsx();
		String sort=searchSanPhamCriteria.getSort();
		long sanPhamId=searchSanPhamCriteria.getSanPhamId();
		
		
		
		//keyword co trong ten
		String keywordCondition="";
		for(String word: keyword) {
			keywordCondition+=" sp.tenSanPham LIKE '%"+word+"%'";
			keywordCondition+=" AND";
		}
		StringBuilder stringBuilder=new StringBuilder(keywordCondition);
		stringBuilder.delete(keywordCondition.length()-3, keywordCondition.length());
		keywordCondition=stringBuilder.toString();
		sql+=keywordCondition;
		System.out.println("keywordCondition:"+keywordCondition);

		if(minValue!=0) {
			sql+=" AND sp.donGia>="+minValue;
		}
		if(maxValue!=-1) {
			sql+=" AND sp.donGia<="+maxValue;
		}
		
		if(danhMuc>0) {
			sql+=" AND danhmuc.id='"+danhMuc+"'";
		}
		
		if(hsx>0) {
			sql+=" AND sp.ma_hang_sx='"+hsx+"'";
		}
		
		if(sanPhamId!=0) {
			sql+=" AND sp.id="+sanPhamId;
		}
		
		if(!sort.equals("")) {
			
			if(sort.equals("newest")) {
				sql+=" ORDER BY sp.id DESC";
			}
			else if(sort.equals("oldest")) {
				sql+=" ORDER BY sp.id ASC";
			}
			else if(sort.equals("priceAsc")) {
				sql+=" ORDER BY sp.donGia ASC";
			}
			else if(sort.equals("priceDes")) {
				sql+=" ORDER BY sp.donGia DESC";
			}

		}
		else {
			sql+=" ORDER BY sp.id ASC";
		}
		
		return sql;
		
	}
	
	public List<SanPham> searchSanPhamByCriteriaConstraint(SearchSanPhamCriteria searchSanPhamCriteria, int noSanPham, int startIndex) {
		
		List<SanPham> resuList=null;
		String sql=this.createSqlCriteria(searchSanPhamCriteria);
		
		sql+=" LIMIT "+noSanPham+" OFFSET "+(startIndex-1);
		sql+=";";
		
		SessionFactory sessionFactory=sessionFactoryUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Query query=session.createNativeQuery(sql, SanPham.class);
		
		System.out.println("sql:"+ sql);
		
		resuList=query.getResultList();
		
		
		return resuList;
		
	}
	
	public List<SanPham> searchSanPhamByCriteria(SearchSanPhamCriteria searchSanPhamCriteria) {
		List<SanPham> resuList=null;
		String sql=this.createSqlCriteria(searchSanPhamCriteria);
		sql+=";";
		
		SessionFactory sessionFactory=sessionFactoryUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Query query=session.createNativeQuery(sql, SanPham.class);
				
		resuList=query.getResultList();
		
		
		return resuList;
	}
	
	public SanPham getSanPhamByID(long id) {
		SessionFactory sessionFactory=this.sessionFactoryUtil.getSessionFactory();
		Session session=sessionFactory.openSession();
		Query sql=session.createNativeQuery("select * from sanpham where id= :id;", SanPham.class);
		sql.setParameter("id", id);
		SanPham sp=(SanPham)sql.uniqueResult();
		
		return sp;
	}
	
	public void SaveOrUpdate(SanPham sp) {
		SanPham tmp=getSanPhamByID(sp.getId());
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();
		if(tmp==null) {
			session.persist(sp);
		}
		else {
			session.merge(sp);
		}
		
		
		transaction.commit();
		
		session.close();
	}
	
	
	
	//search by id
	
}
