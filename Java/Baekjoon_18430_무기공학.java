package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_18430_무기공학 {
	static int[][][] dir = {
			{{0,-1},{0,0},{1,0}}, // ㄱ
			{{-1,0},{0,0},{0,-1}}, // ㄴ뒤집은모양
			{{-1,0},{0,0},{0,1}}, // ㄴ
			{{1,0},{0,0},{0,1}}, // ㄱ 뒤집은모양
	};
	
	
	static int[][] map;
	static boolean[][] visited;
	static int N,M;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		N = Integer.parseInt(init[0]);
		M = Integer.parseInt(init[1]);
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				solve(i,j);
			}
		}
		System.out.println(ans);

	}
	
	private static void dfs(int y, int x, int score) {
		if(y == N && x == M) {
			ans = Math.max(ans, score);
			return;
		}
		else {
			visited[y][x] = true;
			//4개모양 놓아보기
			for (int k = 0; k < 4; k++) {
				int[][] shape = dir[k];
				//int ny = y + shape[0][0];
			}
			visited[y][x] = false;
		}
	}
	
	private static void solve(int y, int x) {
		int sum = 0;
		
		int ny=0,nx=0;
		for (int i = 0; i < 3; i++) {
			ny = y + dir[0][i][0];
			nx = x + dir[0][i][1];
			if(ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]) continue;
			if(i==1) { //가중치
				sum += 2*(map[y][x]);
			}else {
				sum += map[ny][nx];
			}
			visited[ny][nx] = true;
		}
		
		ans = Math.max(ans, sum);
	}

}
