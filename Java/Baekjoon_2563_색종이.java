package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2563_»öÁ¾ÀÌ {
	
	static int[][] map = new int[101][101];
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		cnt = 0;
		for (int t = 0; t < N; t++) {
			String[] info = br.readLine().split(" ");
			int y = Integer.parseInt(info[0]);
			int x = Integer.parseInt(info[1]);
			
			
			for (int i = x; i < x+10; i++) {
				for (int j = y; j < y+10; j++) {
					if(map[i][j]==0) {
						map[i][j] = 1;
						cnt++;
					}
				}
			}
		}
		sb.append(cnt);
		System.out.print(sb);
	}

}
