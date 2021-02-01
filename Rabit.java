package com.baekjoon.algo;

import java.util.Scanner;

public class Rabit {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int total = sc.nextInt();
		int[] dp = new int[total+1];
		
		int[] coins = {1,2,5,7};
		//dp[i] : i(17원) 금액을 조합하는 최소 동전 갯수
		//5원 -> 7원
		//dp[5] -> dp[7]
		for (int i = 1; i <= total; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if(i >= coins[j]) //남은금액이 코인의 종류보다 클때
					//j번 동전을 선택하지 않을경우, j번째 동전을 선택한경우 -> 남아있는 금액의 dp(최적)값에 자기 자신 j번째 동전 더하는 경우 하나 추가 
					dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
			}
		}
		//배낭 문제 - 0 1 
		System.out.println(dp[total]);
	}

}
