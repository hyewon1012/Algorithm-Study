package com.ssafy.off;

public class NumberConversion {
	public static void main(String[] args) {
		int n0 = Integer.parseInt("11", 10); //10진수 -> 10진수
		int n1 = Integer.parseInt("11", 2); //2진수 -> 10진수
		int n2 = Integer.parseInt("11", 8); //8진수 -> 10진수
		
		System.out.printf("%d, %d\n",n1, n2);
		
		String s1 = Integer.toBinaryString(15); // int -> 2진수
		String s2 = Integer.toOctalString(8); // int -> 8진수
		System.out.printf("%s, %s\n",s1, s2);
	}
}
