package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727_2n타일링2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[1001]; // 너비가 n인 직사각형 채우는 경우의 수
		dp[1] = 1;
		dp[2] = 3;
		
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i-1]+dp[i-2]+dp[i-2];
			dp[i] = dp[i]%10007;
		}
		
		System.out.println(dp[N]);
	}

}
