package com.baekjoon.algo;

import java.util.Scanner;

public class Rabit {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int total = sc.nextInt();
		int[] dp = new int[total+1];
		
		int[] coins = {1,2,5,7};
		//dp[i] : i(17��) �ݾ��� �����ϴ� �ּ� ���� ����
		//5�� -> 7��
		//dp[5] -> dp[7]
		for (int i = 1; i <= total; i++) {
			dp[i] = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if(i >= coins[j]) //�����ݾ��� ������ �������� Ŭ��
					//j�� ������ �������� �������, j��° ������ �����Ѱ�� -> �����ִ� �ݾ��� dp(����)���� �ڱ� �ڽ� j��° ���� ���ϴ� ��� �ϳ� �߰� 
					dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
			}
		}
		//�賶 ���� - 0 1 
		System.out.println(dp[total]);
	}

}
