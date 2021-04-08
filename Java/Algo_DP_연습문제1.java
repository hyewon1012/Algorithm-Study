package com.ssafy.off;

import java.util.Arrays;

public class Algo_DP_연습문제1 {

	public static void main(String[] args) {
		//dp[n] : n층에 색칠할수있는 경우의 수
		//dp[n] = dp[n-1]*2-(n-2) 파란색 연속하는경우 하나 빼주기
		//노란색 인접층 연속 가능
		//파란색 인접층 연속 불가능
		int[] dp = new int[1001];
		Arrays.fill(dp, -1);
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= 1000; i++) {
			dp[i] = dp[i-1]*2-(i-2);
		}
		System.out.println(dp[8]);

		solve();
	}
	
	private static void solve() {
		int N = 8;
		int[][] dp = new int[N+1][2];
		dp[1][0] = 1; // 1층에서 사용할 수 있는 노랑색 경우의수
		dp[1][1] = 1; // 1층에서 사용할 수 있는 파랑색 경우의수
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i-1][0]+dp[i-1][1];
			dp[i][1] = dp[i-1][0];
		}
		System.out.println(dp[8][0]+dp[8][1]);
	}

}
