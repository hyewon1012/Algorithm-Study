package com.ssafy.off;

public class Algo_DP_연습문제2 {

	public static void main(String[] args) {
		
		int N = 6;
		//1. 구해야 되는 답을 문장으로 써보기. 함수의 역할을 명확하게 정의
		int[] dp = new int[N+1];
		dp[1] = 2;
		dp[2] = 5;
		for (int i = 3; i <= N; i++) {
			//전체 길이가 N인 경우 맨 뒤에 노랑,파랑,빨강이 올 경우
			dp[i] = dp[i-1] + dp[i-1] + dp[i-2];
		}
		System.out.println(dp[N]);
	}

}
