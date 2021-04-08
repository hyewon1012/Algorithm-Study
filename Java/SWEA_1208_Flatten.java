package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1208_Flatten {
	static int dump;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = 10;
		for (int t = 1; t <= TC; t++) {
			dump = Integer.parseInt(br.readLine()); // 덤프횟수
			int [] heights = new int[100];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 100; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(heights);
			for (int i = 0; i < dump; i++) {
				heights[99]--;
				heights[0]++;
				Arrays.sort(heights);
			}
			int ans = heights[99] - heights[0];
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);

	}

}
