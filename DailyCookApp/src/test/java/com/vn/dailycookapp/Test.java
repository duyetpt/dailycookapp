package com.vn.dailycookapp;

import java.io.UnsupportedEncodingException;

import com.vn.dailycookapp.dao.CategoryDAO;
import com.vn.dailycookapp.dao.DAOException;
import com.vn.dailycookapp.entity.Category;

public class Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException, DAOException {
		{
			Category category = new Category();
			category.setName("Ways_of_cook");
			category.setValue("Ways of cook");
			
			CategoryDAO categoryDao = CategoryDAO.getInstance();
			categoryDao.save(category);
			
			// child 2
			Category ct2 = new Category();
			ct2.setName("Grill");
			ct2.setValue("Grill");
			ct2.setParentId(category.getId());
			
			// child 3
			Category ct3 = new Category();
			ct3.setName("Bake");
			ct3.setValue("Bake");
			ct3.setParentId(category.getId());
			
			categoryDao.save(ct2);
			categoryDao.save(ct3);
		}
		
		{
			Category category = new Category();
			category.setName("Main_ingredient");
			category.setValue("Main ingredient");
			
			CategoryDAO categoryDao = CategoryDAO.getInstance();
			categoryDao.save(category);
			
			// child 1
			Category ct1 = new Category();
			ct1.setName("Meat");
			ct1.setValue("Meat");
			ct1.setParentId(category.getId());
			
			// child 2
			Category ct2 = new Category();
			ct2.setName("Seafood");
			ct2.setValue("Seafood");
			ct2.setParentId(category.getId());
			
			// child 3
			Category ct3 = new Category();
			ct3.setName("Vegetables_fruits");
			ct3.setValue("Vegetables, fruits");
			ct3.setParentId(category.getId());
			
			categoryDao.save(ct1);
			categoryDao.save(ct2);
			categoryDao.save(ct3);
		}
	}
	
}
