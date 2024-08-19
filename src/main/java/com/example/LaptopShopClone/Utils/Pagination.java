package com.example.LaptopShopClone.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pagination {
	private int noAllowedPage;//only allow odd number for now, need upgrade
	private int noResultPerPage;
	private int totalResult;
	private int totalPage;
	private int halfAllowedPage;
	
	
	public Pagination(int noAllowedPage, int noResultPerPage, int totalResult, int totalPage) {
		super();
		this.noAllowedPage = noAllowedPage;
		this.noResultPerPage = noResultPerPage;
		this.totalResult = totalResult;
		this.totalPage = totalPage;
		this.halfAllowedPage = (int)noAllowedPage/2;
	}
	
	public List<Integer> generatePageList(int currentPage) {
		List<Integer> pageList=new ArrayList<Integer>();
		if(currentPage>=1 && currentPage<5) {
			for(int i=1; i<=noAllowedPage && i<=totalPage; ++i)
				pageList.add(i);
		}
		else if(currentPage==totalPage) {
			for(int i=currentPage; i>totalPage-noAllowedPage &&i>0; --i)
				pageList.add(i);
			Collections.sort(pageList);
		}
		else {
			//left side
			for(int i=1; i<=halfAllowedPage; ++i)
				pageList.add(currentPage-i);
			Collections.sort(pageList);
			pageList.add(currentPage);
			//right side
			for(int i=1; i<=halfAllowedPage; ++i)
				pageList.add(currentPage+i);
			
		}
		
		return pageList;
	}

	
	
}
