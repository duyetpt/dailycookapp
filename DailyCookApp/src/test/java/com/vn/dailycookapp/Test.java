package com.vn.dailycookapp;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.vn.dailycookapp.entity.Recipe;
import com.vn.dailycookapp.entity.Recipe.Ingredient;

public class Test {
	
//	public static void main(String[] args) throws UnsupportedEncodingException, DAOException {
//		{
//			Category category = new Category();
//			category.setName("Ways_of_cook");
//			category.setValue("Ways of cook");
//			
//			CategoryDAO categoryDao = CategoryDAO.getInstance();
//			categoryDao.save(category);
//			
//			// child 2
//			Category ct2 = new Category();
//			ct2.setName("Grill");
//			ct2.setValue("Grill");
//			ct2.setParentId(category.getId());
//			
//			// child 3
//			Category ct3 = new Category();
//			ct3.setName("Bake");
//			ct3.setValue("Bake");
//			ct3.setParentId(category.getId());
//			
//			categoryDao.save(ct2);
//			categoryDao.save(ct3);
//		}
//		
//		{
//			Category category = new Category();
//			category.setName("Main_ingredient");
//			category.setValue("Main ingredient");
//			
//			CategoryDAO categoryDao = CategoryDAO.getInstance();
//			categoryDao.save(category);
//			
//			// child 1
//			Category ct1 = new Category();
//			ct1.setName("Meat");
//			ct1.setValue("Meat");
//			ct1.setParentId(category.getId());
//			
//			// child 2
//			Category ct2 = new Category();
//			ct2.setName("Seafood");
//			ct2.setValue("Seafood");
//			ct2.setParentId(category.getId());
//			
//			// child 3
//			Category ct3 = new Category();
//			ct3.setName("Vegetables_fruits");
//			ct3.setValue("Vegetables, fruits");
//			ct3.setParentId(category.getId());
//			
//			categoryDao.save(ct1);
//			categoryDao.save(ct2);
//			categoryDao.save(ct3);
//		}
//	}
	
	List<String> stringList = new ArrayList<String>();
    List<Integer> integerList = new ArrayList<Integer>();

    public static void main(String... args) throws Exception {
        Field stringListField = Test.class.getDeclaredField("stringList");
        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = Test.class.getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.
        
    }
}
