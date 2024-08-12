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
import com.example.LaptopShopClone.Utils.SearchDonHangCriteria;
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
		Query sql=session.createNativeQuery("select count(*) from donhang where trangThaiDonHang=:trangThaiDonHang", Integer.class);
		sql.setParameter("trangThaiDonHang", trangThai);
		
		
		return (Integer)sql.uniqueResult();
		
	}
	
	public int getTotalNumberDonHang() {
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sql=session.createNativeQuery("select count(*) from donhang;", Integer.class);
		
		return (Integer)sql.uniqueResult();
	}
	
	public String generateSQLDonHangFromCriteria(SearchDonHangCriteria searchDonHangCriteria) {
		 String sql="select * from donhang where 1";
		 
		 if(searchDonHangCriteria.getTrangThaiDonHang()!=null && !searchDonHangCriteria.getTrangThaiDonHang().equals("") && !searchDonHangCriteria.getTrangThaiDonHang().equals("Tất cả")) {
			 sql+=" AND donhang.trangThaiDonHang='"+searchDonHangCriteria.getTrangThaiDonHang()+"'";
		 }
		 
		 if(searchDonHangCriteria.getStartDate()!=null) {
			 sql+=" AND donhang.ngayDatHang>='"+searchDonHangCriteria.getStartDate()+"'";
		 }
		 if(searchDonHangCriteria.getEndDate()!=null) {
			 sql+=" AND donhang.ngayDatHang<='"+searchDonHangCriteria.getEndDate()+"'";
		 }
		 if(searchDonHangCriteria.getId()!=0) {
			 sql+=" AND donhang.id="+searchDonHangCriteria.getId();
		 }
		
		 sql+=" ORDER BY donhang.id DESC";
		 
		 return sql;
	 }
	
	public String generateSQLCountDonHangFromCriteria(SearchDonHangCriteria searchDonHangCriteria) {
		 String sql="select count(*) from donhang where 1";
		 
		 if(searchDonHangCriteria.getTrangThaiDonHang()!=null && !searchDonHangCriteria.getTrangThaiDonHang().equals("") && !searchDonHangCriteria.getTrangThaiDonHang().equals("Tất cả")) {
			 sql+=" AND donhang.trangThaiDonHang='"+searchDonHangCriteria.getTrangThaiDonHang()+"'";
		 }
		 
		 if(searchDonHangCriteria.getStartDate()!=null) {
			 sql+=" AND donhang.ngayDatHang>='"+searchDonHangCriteria.getStartDate()+"'";
		 }
		 if(searchDonHangCriteria.getEndDate()!=null) {
			 sql+=" AND donhang.ngayDatHang<='"+searchDonHangCriteria.getEndDate()+"'";
		 }
		 if(searchDonHangCriteria.getId()!=0) {
			 sql+=" AND donhang.id="+searchDonHangCriteria.getId();
		 }
		
		 sql+=" ORDER BY donhang.id DESC";
		 
		 return sql;
	 }
	
	public List<DonHang> getDonhangByCriteriaPageConstraint(SearchDonHangCriteria searchDonHangCriteria, int offset, int limit){
		String sql=this.generateSQLDonHangFromCriteria(searchDonHangCriteria);
		sql+=" LIMIT :limit OFFSET :offset";
		
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sqlQuery=session.createNativeQuery(sql, DonHang.class);
		sqlQuery.setParameter("limit", limit);
		sqlQuery.setParameter("offset", offset);
		
		
		return sqlQuery.getResultList();
		
		
		
	}
	
	public int getTotalNumberDonHangByCriteria(SearchDonHangCriteria searchDonHangCriteria) {
		String sql=this.generateSQLCountDonHangFromCriteria(searchDonHangCriteria);
		Session session=this.sessionFactoryUtil.getSessionFactory().openSession();
		Query sqlQuery=session.createNativeQuery(sql, Integer.class);
		
		return (Integer)sqlQuery.uniqueResult();
		
	}
	
}
