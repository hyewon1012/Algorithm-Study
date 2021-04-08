package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1189_컴백홈 {
	static int R,C,K;
	static char[][] map;
	static boolean[][] visited;
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,1,-1};
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[j];
			}
		}
		visited = new boolean[R][C];
		visited[R-1][0] = true;
		ans = 0;
		solve(R-1,0,1);
		System.out.println(ans);
		

	}
	private static void solve(int y, int x, int cnt) {
		if(cnt > K) return;
		if(y == 0 && x == C-1) {
			if(cnt == K) {
				ans+=1;
			}
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny >= R || ny < 0 || nx >= C || nx < 0) continue;
			if(map[ny][nx] == '.' && !visited[ny][nx]) {
				visited[ny][nx] = true;
				solve(ny,nx,cnt+1);
				visited[ny][nx] = false;
			}
			
		}
		
	}

}
