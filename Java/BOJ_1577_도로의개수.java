package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1577_도로의개수 {
	static int R,C;
	static int K; // 공사중인 도로의 개수
	static int map[][];

	static int[][] dp;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken()); // 가로
		R = Integer.parseInt(st.nextToken()); // 세로

		K = Integer.parseInt(br.readLine());
		
		map = new int[101][101];

		dp = new int[101][101];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			if(x1 != 0 && y1 != 0) map[y1][x1] = -1;
			if(x2 != C && y2!= R) map[y2][x2] = -1;
			
		}
		dp[0][0] = 1;
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if(map[i][j] == 0) {
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}else {
					dp[i][j] = 0;
				}
				
			}
		}
		
		System.out.println(dp[R][C]);
		
	}


}
