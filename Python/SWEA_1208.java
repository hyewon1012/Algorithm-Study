package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Flatten
 * */
public class SWEA_1208 {

	public static void solve2() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int dumpCnt = Integer.parseInt(in.readLine());
			Integer[] height = new Integer[100];
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			for (int i = 0; i < 100; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			//solve
			Arrays.sort(height, Collections.reverseOrder());//내림차순
			while(dumpCnt-- > 0) {
				--height[0];
				++height[99];
				Arrays.sort(height, Collections.reverseOrder());//내림차순
			}
			
			System.out.println("#"+t+" "+(height[0]-height[99]));
		}
	}
	
	public static void main(String[] args) {
		//덤프횟수 0 -> 최고점 최저점 높이 차 반환
//		Scanner sc = new Scanner(System.in);
//		
//		for (int t = 1; t <= 10; t++) {
//			int dumpCnt = sc.nextInt();
//			Integer[] height = new Integer[100];
//			
//			for (int i = 0; i < 100; i++) {
//				height[i] = sc.nextInt();
//			}
//			
//			//solve
//			Arrays.sort(height, Collections.reverseOrder());//내림차순
//			while(dumpCnt-- > 0) {
//				--height[0];
//				++height[99];
//				Arrays.sort(height, Collections.reverseOrder());//내림차순
//			}
//			
//			System.out.println("#"+t+" "+(height[0]-height[99]));
//		}
		try {
			solve2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
