package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_2309_¿œ∞ˆ≥≠¿Ô¿Ã {
	static int[] arr;
	static int[] remain;
	static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		remain = new int[7];
		
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		comb(0,0);
		
	}
	
	public static void comb(int cnt, int idx) {
		if(cnt == 7) {
			int total = 0;
			for (int i = 0; i < 7; i++) {
				total += remain[i];
			}
			if(total == 100) {
				Arrays.sort(remain);
				for (int i = 0; i < 7; i++) {
					System.out.println(remain[i]);
				}
			}
			return;
		}
		else {
			for (int i = idx; i < 9; ++i) {
				remain[cnt] = arr[i];
				comb(cnt+1, i+1);
			}
		}

	}

}
