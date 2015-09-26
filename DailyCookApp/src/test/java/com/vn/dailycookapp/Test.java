package com.vn.dailycookapp;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "Phạm Thế Duyệt";
		 String s1 = Normalizer.normalize(s, Normalizer.Form.NFKD);
		    String regex = "[\\p{InCombiningDiacriticalMarks}}]+";

		    String s2 = new String(s1.replaceAll(regex, "").getBytes("ascii"), "ascii");

		    System.out.println(s2);
		    System.out.println("Pham The Duyet".equals(s2));
	}

	 
}
