package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_2116_주사위쌓기 {
	static int N;
	static int[][] dice;
	static int[][] dir = {
			{0,2,3,4,1,5}, //90도 왼쪽기준
			{0,3,4,1,2,5}, //180도
			{0,4,1,2,3,5} //270e도
	};
	static boolean[] isSelected;
	static int[] order;
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 주사위 개수
		dice = new int[N][6];
		

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			isSelected = new boolean[N];
//			order = new int[N];
//			build(0, dice[i][0]);
//		}
		
		for (int i = 1; i <= 6; i++) {
			build(0,i,0);
		}
		
		System.out.println(ans);		
		
		
	}
	//dice[i][0] : 윗면 , dice[i][5] : 아랫면
	private static void build(int cnt, int now, int sum) {
		if(cnt == N) {
			System.out.println(Arrays.toString(order));
			int total = 0;
			//기둥 한쪽면 최대값 찾기 (한쪽면에 최대값몰아준다)
			for (int i = 0; i < N; i++) {
				int max = 0;
				for (int j = 1; j <= 4; j++) {
					max = Math.max(max, dice[order[i]][j]);
				}
				total += max;
				ans = Math.max(ans, total);
			}
			return;
		}
		
		int next = 0;
		switch (now) {
		case 0:
			next = 5;
			break;
		case 1:
			next = 3;
		}
		

	}


}
