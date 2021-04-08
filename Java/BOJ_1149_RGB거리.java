package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[1001][3];
		int[][] cost = new int[1001][3];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cost[i][0] = Integer.parseInt(st.nextToken()); // R 비용
			cost[i][1] = Integer.parseInt(st.nextToken()); // G 비용
			cost[i][2] = Integer.parseInt(st.nextToken()); // B 비용
		}
		dp[1][0] = cost[1][0];
		dp[1][1] = cost[1][1];
		dp[1][2] = cost[1][2];
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0]; //i번째 집을 빨간색으로 칠했을때
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1]; //i번째 집을 초록색으로 칠했을때
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2]; //i번째 집을 파랑색으로 칠했을때
		}
		int ans = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]); // 세가지중 가장 최소 비용
		System.out.println(ans);

	}

}
