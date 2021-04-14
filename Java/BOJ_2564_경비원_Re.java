package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564_경비원_Re {
	static int R,C,N;
	static int[] info;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		info = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 1 2 3 4 북 남 서 동 
			// (0,0) -> 시계방향으로
			switch (a) {
				case 1: // 북
					info[i] = b;
					break;
				case 2: // 남
					info[i] = C+R+(C-b);
					break;
				case 3: // 서
					info[i] = C+R+C+(R-b);
					break;
				case 4: // 동
					info[i] = C+b;
					break;
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int dir = Integer.parseInt(st.nextToken());
		int loc = Integer.parseInt(st.nextToken());
		int total = 0; //도착지까지 가야하는 길이
		switch (dir) {
			case 1: // 북
				total = loc;
				break;
			case 2: // 남
				total = C+R+C-loc;
				break;
			case 3: // 서
				total = C+R+C+R-loc;
				break;
			case 4: // 동
				total = C+loc;
				break;
		}
		System.out.println(solve(total));
	}
	private static int solve(int total) {
		int ans = 0;
		int clockwise = 0;
		for (int i = 0; i < N; i++) {
			clockwise = Math.abs(total-info[i]);
			//시계방향 vs 반시계방향 큰거 찾기
			ans += Math.min(clockwise, 2*(R+C)-(clockwise));
		}
		return ans;
	}


}
