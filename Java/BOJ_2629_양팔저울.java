package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2629_양팔저울 {
	static int N,M;
	static int[] weight; // 추
	static int[] beads; // 구슬
	static boolean[][] dp; // dp[i][j] : i번까지 추를 확인했을때 무게 j 를 만들수있다면 true
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		weight = new int[31];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		beads = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			beads[i] = Integer.parseInt(st.nextToken());
		}
		dp = new boolean[31][15001];
		
		solve(0, 0);
		for (int i = 0; i < M; i++) {
			if(beads[i] > 15000) { // 만들수있는 최고무게보다 더 많이 나가면 못만드는 경우
				sb.append("N").append(" ");
			}
			else if(dp[N][beads[i]] == true) {
				sb.append("Y").append(" ");
			}else {
				sb.append("N").append(" ");
			}
		}
		System.out.print(sb);
	}
	
	private static void solve(int i, int w) {
		if(i > N) return;
		//i번까지 추를 확인해서 w 무게를 만들었으면 바로 리턴
		if(dp[i][w] == true) return;
		dp[i][w] = true;
		
		solve(i+1, w + weight[i]); // 추만있는쪽에 추 올리기
		solve(i+1, Math.abs(w-weight[i])); // 구슬쪽에 추 올리기
		solve(i+1, w); // 아무것도 안 올리기
	}

}
