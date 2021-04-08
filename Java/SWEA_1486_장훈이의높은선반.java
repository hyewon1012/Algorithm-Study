package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
2^20 부분집합 가능
*/
public class SWEA_1486_장훈이의높은선반 {
	static int N;
	static int B;
	static int[] height;
	static boolean[] isSelected;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			height = new int[N];
			isSelected = new boolean[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				subset(0, i);
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}// end of test case
		
		System.out.print(sb);

	}
	private static void subset(int cnt, int pick) {
		if(cnt == pick) {
			int total = 0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					total += height[i];
				}
			}
			if(total >= B) {
				ans = Math.min(ans, total-B);
			}
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1, pick);
		isSelected[cnt] = false;
		subset(cnt+1, pick);
	}
	
	private static void byBitMasking() {
		for (int i = 1; i < (1<<N); i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if( (i & (1<<j)) > 0) { // i에 j비트가 포함된다면
					sum += height[j];
					//이미 만족했다면 멈추기
					if(sum-B > ans) {
						break;
					}
				}
			}
			//B를 넘으면서 차이는 최소화
			if(sum >= B && sum-B < ans) {
				ans = sum-B;
			}
		}
	}

}
