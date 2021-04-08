package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 봉지최소개수
 * dp[n] : n킬로그램 배달하는데 3kg,5kg 봉지 최소 개수 합
 * dp[0] = 0
 * dp[3] = 1
 * dp[5] = 1
 * */
public class Baekjoon_2839_설탕배달 {
	
	static int answer = 0;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		int N = Integer.parseInt(br.readLine());
		dp = new int[5001];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		dp[3] = 1;
		dp[5] = 1;
		
		if(N <= 5) {
			System.out.println(dp[N]);
		}else {
			for (int i = 6; i <= N; i++) {
				if(dp[i-3] != -1 && dp[i-5] != -1) {
					dp[i] = Math.min(dp[i-3],dp[i-5])+1;
				}
				if(dp[i-3] != -1 && dp[i-5] == -1) {
					dp[i] = dp[i-3] + 1;
				}
				if(dp[i-3] == -1 && dp[i-5] != -1) {
					dp[i] = dp[i-5] + 1;
				}
				
			}
			System.out.println(dp[N]);
		}
		


	}


}
