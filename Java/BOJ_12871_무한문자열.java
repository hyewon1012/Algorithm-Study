package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12871_무한문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String other = br.readLine();
		
		check(origin, other);
	}
	
	private static void check(String s1, String s2) {
		int s1Length = s1.length();
		int s2Length = s2.length();
		
		int length = lcm(s1Length, s2Length);
		
		StringBuilder sb1 = new StringBuilder(s1);
		StringBuilder sb2 = new StringBuilder(s2);
		for (int i = 0; i < length/s1Length-1; i++) {
			sb1.append(s1);
		}
		for (int i = 0; i < length/s2Length-1; i++) {
			sb2.append(s2);
		}
		if(sb1.toString().equals(sb2.toString())) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}
	private static int gcd(int a, int b) {
		if(b==0) return a;
		return gcd(b, a%b);
	}
	private static int lcm(int a, int b) {
		return a*b / gcd(a,b);
	}
}
