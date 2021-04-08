package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm_12_식당예제 {

	static int N,M;
	static char[][] map;
	static boolean[][] visited;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		N = Integer.parseInt(init[0]);
		M = Integer.parseInt(init[1]);
		map = new char[N][M];
		visited = new boolean[N][M];
		ans = 0;
		
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j];
			}
		}
		for (int i = 0; i < N; i++) {
			ans += backTracking(i, 0);
		}

		System.out.println(ans);
		
	}
	
	private static int backTracking(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= M || map[y][x] != '.') return 0;
		
		if(x == M-1) {
			return 1;
		}
		//가지칠수있는경우
		if(!visited[y][x] && map[y][x] == '.') {
			visited[y][x] = true;
			//위
			if(backTracking(y-1, x+1) == 1) {
				return 1;
			}
			//수평
			if(backTracking(y, x+1) == 1) {
				return 1;
			}
			//아래
			if(backTracking(y+1, x+1) == 1) {
				return 1;
			}
			
		}
		return 0;
		
	}

}
