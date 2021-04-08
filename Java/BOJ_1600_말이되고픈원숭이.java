package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {
	static int K;
	static int R,C;
	static int[][] map;
	static int[][][] distance;
	static int dy[] = {1,-1,0,0,2,1,-1,-2,-2,-1,1,2};
	static int dx[] = {0,0,-1,1,1,2,2,1,-1,-2,-2,-1};
	static int ans;
	
	static class Pos{
		int y;
		int x;
		int val;
		
		public Pos(int y, int x, int val) {
			super();
			this.y = y;
			this.x = x;
			this.val = val;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		distance = new int[R][C][K+1];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		distance[0][0][0] = 0;
		byBFS(0,0);
		
	}
	
	private static void byBFS(int y, int x) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(y,x,0));
		while(!q.isEmpty()) {
			Pos now = q.poll();
 
			int limit = 0;
			if(now.val == K) {
				limit = 4;
			}else {
				limit = 12;
			}
			
			if(now.y == R-1 && now.x == C-1) {
				System.out.println(distance[now.y][now.x][now.val]);
				return;
			}
			
			for (int i = 0; i < limit; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				int nextVal = i < 4 ? now.val : now.val+1;

				if(ny < R && ny >= 0 && nx < C && nx >= 0) {
					if(map[ny][nx] == 0 && distance[ny][nx][nextVal] == 0) {
						distance[ny][nx][nextVal] = distance[now.y][now.x][now.val] + 1;
						q.add(new Pos(ny,nx,nextVal));
					}
				}
			}

		}
		System.out.println("-1");
	}
	
//	private static void solve(int k, int y, int x, int cnt) {
//		visited[y][x] = true;
//		if(cnt > ans) return;
//		if(y == R-1 && x == C-1) {
//			if(cnt < ans) ans = cnt;
//			return;
//		}
//		int ny=0,nx=0;
//		
//		// 말처럼 이동할수있을때 
//		// 말처럼 이동한다 말처럼 이동안한다
//		if(k > 0) {
//			for (int i = 0; i < 8; i++) {
//				ny = y + hy[i];
//				nx = x + hx[i];
//				if(ny < R && ny >= 0 && nx < C && nx >= 0 && !visited[ny][nx] && map[ny][nx] == 0) {
//					visited[ny][nx] = true;
//					solve(k-1, ny, nx, cnt+1);
//					visited[ny][nx] = false;
//				}
//			}
//			for (int i = 0; i < 4; i++) {
//				ny = y + dy[i];
//				nx = x + dx[i];
//				if(ny < R && ny >= 0 && nx < C && nx >= 0 && !visited[ny][nx] && map[ny][nx] == 0) {
//					visited[ny][nx] = true;
//					solve(k, ny, nx, cnt+1);
//					visited[ny][nx] = false;
//				}
//			}
//		}
//				
//	}

}
