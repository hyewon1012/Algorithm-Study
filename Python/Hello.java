package com.baekjoon.algo;

import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] hp = new int[21];
		int[] joy = new int[21];
		
		int[][] dp = new int[21][101];
		
		//�Էºκ�
		for (int j = 1; j <= N; j++) {
			hp[j] = sc.nextInt();
			
		}
		for (int j = 1; j <= N; j++) {
			joy[j] = sc.nextInt();
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 99; j++) {
				//i������ �λ縦 �Ҽ��ִ� ���
				if(hp[i] <= j)
					//i���� �λ���ϴ°��, i���� �λ��ϴ°�� �� �ִ밪
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-hp[i]]+joy[i]);
				else
					dp[i][j] = dp[i-1][j];
			}
		}
		
		System.out.println(dp[N][99]);

	}
	


}
