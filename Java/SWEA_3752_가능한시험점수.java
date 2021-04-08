package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3752_가능한시험점수 {
	static int N;
	static int total;
	static int[] scores;
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			scores = new int[N];
			total = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
				total += scores[i];
			}
			visited = new boolean[total+1];
			ans = 0;
			visited[0] = true; // 0점일때
			solve(0,total);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}// end of test case
		System.out.print(sb);
	}
	
	private static void solve(int n, int max) {
		if(n == N) {
			for (boolean bool : visited) {
				if(bool) {
					ans++;
				}
			}
			return;
		}
		
		for (int i = max; i >= 0; i--) {
			if(visited[i]) {
				visited[i+scores[n]] = true;
			}
		}
		solve(n+1, max);
		
	}
}
