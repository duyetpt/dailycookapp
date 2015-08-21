package com.vn.dailycookapp;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Test {
	
	private SecureRandom	random	= new SecureRandom();
	private BigInteger		bigInt;
	
	public Test() {
		
	}
	
	public String nextSessionId() {
		bigInt = new BigInteger(130, random);
		System.out.println(bigInt.toString());
		return bigInt.toString(2);
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(test.nextSessionId());
		System.out.println(test.nextSessionId());
	}
}
