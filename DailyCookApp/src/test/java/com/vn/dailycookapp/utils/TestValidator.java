package com.vn.dailycookapp.utils;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestValidator {
	
	static Validator validtor;
	
	@BeforeClass
	public static void init() {
		validtor = Validator.getInstance();
	}
	
	@Test
	public void testIsNotNullFunction() {
		assertEquals(validtor.isNotNull(null), false);
		assertEquals(validtor.isNotNull(""), true);
	}
	
	@Test
	public void testIsNullFunction() {
		assertEquals(validtor.isNull(null), true);
		assertEquals(validtor.isNull(""), false);
	}
	
	@Test
	public void testValidateEmail() {
		assertEquals(true, validtor.isValidEmail("duyept@gmail.com"));
		assertEquals(true, validtor.isValidEmail("duyept12@gmail.com"));
		assertEquals(true, validtor.isValidEmail("duyepsdft@gmail.com"));
		assertEquals(true, validtor.isValidEmail("duyept@hotmail.com"));
		assertEquals(true, validtor.isValidEmail("duyept@yahoo.com"));
	}
	
	@Test
	public void testInvalidateEmail() {
		assertEquals(false, validtor.isValidEmail(""));
		assertEquals(false, validtor.isValidEmail("duyetpt@"));
		assertEquals(false, validtor.isValidEmail("@xxxxx"));
		assertEquals(false, validtor.isValidEmail("abcabc"));
		assertEquals(false, validtor.isValidEmail("ac@abc@"));
	}
}
