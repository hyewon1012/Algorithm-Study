package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토 {
	static int R,C;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		
		q = new LinkedList<int[]>();
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.add(new int[] {i, j, 0});
					visited[i][j] = true;
				}
			}
		}
		System.out.println(byBFS());
		
	}
	
	private static int byBFS() {
		int day = -1;
		//토마토 다 익었는지 체크
		boolean flag = true;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 0) {
					flag = false;
				}
			}
		}
		if(flag) return 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] now = q.poll();
				int y = now[0], x = now[1]; 
				day = now[2];
				map[y][x] = 1; // 토마토 익음
				
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny >= R || ny < 0 || nx >= C || nx < 0) continue;
					if(!visited[ny][nx] && map[ny][nx] == 0) {
						visited[ny][nx] = true;
						q.add(new int[] {ny,nx,day+1});
					}
				}
				
			}
		}
		
		//토마토 다 익었는지 체크
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 0) {
					day = -1;
				}
			}
		}
		
		return day;
	}

}
