package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA_1249_보급로 {
	static int N;
	static int[][] map;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	
	static class Pos implements Comparable<Pos>{
		int y,x,cost;
		
		public Pos(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String[] temp = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(temp[j]);
				}
			}
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		System.out.println(sb);

	}
	private static int solve() {
		boolean[][] visited = new boolean[N][N];
		int ans = 0;
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		q.add(new Pos(0,0,0));
		
go:		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Pos now = q.poll();
				
				if(now.y == N-1 && now.x == N-1) {
					ans = now.cost;
					break go;
				}
				
				if(visited[now.y][now.x]) continue;
				visited[now.y][now.x] = true;
				
				for (int d = 0; d < 4; d++) {
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];
					if(ny >= N || ny < 0 || nx >= N || nx < 0) continue;
					if(!visited[ny][nx]) {
						//visited[][] = true
						q.add(new Pos(ny, nx, now.cost + map[ny][nx]));
					}
				}

			}
		}
		return ans;
	}

}
