package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MemoryRecover {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tc = in.read();
		
		for (int t = 1; t <= tc; t++) {
			char[] ch = in.readLine().toCharArray();
			for (char c : ch) {
				System.out.print(c);
			}
		}

		
//		Scanner sc = new Scanner(System.in);
//		int tc = sc.nextInt();
		
//		for (int t = 1; t <= tc; t++) {
//			
//			String s = sc.next();
//			int[] s_temp = new int[s.length()];
//			
//			for (int i = 0; i < s.length(); i++) {
//				s_temp[i] = s.charAt(i) - '0';
//			}			
//			//0011
//			//010101 : 011111 111111 000000
//			//011100 : 011111 111111 000000
//			//인접한게 다른것만큼 +1
//			int cnt = s_temp[0];
//			for (int i = 1; i < s.length(); i++) {
//				if(s_temp[i] != s_temp[i-1]) {
//					cnt++;
//				}
//			}
//			System.out.println("#"+t+" "+cnt);
//		}

	}

}
