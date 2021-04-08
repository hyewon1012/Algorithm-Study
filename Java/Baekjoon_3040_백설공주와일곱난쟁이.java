package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_3040_归汲傍林客老蚌抄里捞 {
	static final int N = 7;
	static int[] numbers;
	static int[] result;
	static boolean isSelected[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		numbers = new int[9];
		result = new int[7];
		for (int i = 0; i < 9; i++) {
			numbers[i] = Integer.parseInt(br.readLine()); 
		}
		comb(0,0);

	}
	
	private static void comb(int cnt, int start) {
		
		if(cnt == N) {
			int sum = 0;
			for (int i = 0; i < result.length; i++) {
				sum += result[i];
			}
			if(sum==100) {
				//Arrays.sort(result);
				for (int i = 0; i < result.length; i++) {
					System.out.println(result[i]);
				}
			}
			return;
		}
		else {
			for (int i = start; i < 9; i++) {
				result[cnt] = numbers[i];
				comb(cnt+1, i+1);
			}
		}
	}

}
