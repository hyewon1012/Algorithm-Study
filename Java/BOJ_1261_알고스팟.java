package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * r,c 에 내가 몇번 벽을 부수고 왔는지 -> 관리할 상태가 3개
 * 최단, 최소 -> 큐 BFS 비용으로 찾기
 * 1 1 1
 * 0 0 0
 * 1 1 1
 * visited[r][c]
 * */
public class BOJ_1261_알고스팟 {
	static int R,C;
	static int[][] map;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static class Pos implements Comparable<Pos>{
		int r,c,cnt;
		public Pos(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pos o) {
			return this.cnt - o.cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		boolean[][] visited = new boolean[R][C];
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
		pq.add(new Pos(0,0,0));
		int time = 0;
		while(!pq.isEmpty()) {
			int size = pq.size();
			while(size-- > 0) {
				Pos now = pq.poll();
				
				if(visited[now.r][now.c]) continue;
				visited[now.r][now.c] = true;
				
				if(now.r == R-1 && now.c == C-1) {
					return now.cnt;
				}
				for (int d = 0; d < 4; d++) {
					int nr = now.r + dy[d];
					int nc = now.c + dx[d];
					if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
					if(!visited[nr][nc]) {
						pq.add(new Pos(nr, nc, now.cnt + map[nr][nc]));
					}
				}
				
			}
			time++;
		}
		return -1;
	}

}
