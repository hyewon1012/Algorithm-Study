package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static int R,C;
	static int my,mx;
	static int time;
	static int[][] map;

	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int up = 1<<3; 
	static int down = 1<<2; 
	static int left = 1<<1; 
	static int right = 1<<0;
	
	static int[] pipe = {
			0, 
			up|down|left|right,
			up|down,
			left|right,
			up|right,
			down|right,
			down|left,
			up|left
	};
	
	static class Pos{
		int y,x,t;

		public Pos(int y, int x, int t) {
			super();
			this.y = y;
			this.x = x;
			this.t = t;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			my = Integer.parseInt(st.nextToken());
			mx = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {
						map[i][j] = pipe[map[i][j]];
					}
				}
			}
			
			
			
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
//			int test = 0;
//			System.out.println();
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(visited[i][j] + " ");
//					if(visited[i][j]) test++;
//				}
//				System.out.println();
//			}
//			System.out.println("test : " + test);
		}
		System.out.println(sb);

	}
	private static int solve() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		q.add(new Pos(my,mx,1));
		visited[my][mx] = true;
		
		int cnt = 1;

		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Pos now = q.poll();
				int y = now.y;
				int x = now.x;
				int pipeNum = map[y][x]; // 파이프 방향 비트 조합 상태 (상하좌우)
				
				if(now.t == time) return cnt;
				
				for (int i = 0; i < 4; i++) { // i : 3 상 ,2 하,1 좌, 0 우
					if((pipeNum & (1<<i)) > 0) {
						int ny = y + dy[i];
						int nx = x + dx[i];

						if(ny >= R || ny < 0 || nx >= C || nx < 0) continue;
						//파이프 연결성 체크
						int nextPipe = map[ny][nx];
						if(map[ny][nx] > 0 && !visited[ny][nx]) {
							if(check(nextPipe, i)) {
								visited[ny][nx] = true;
								q.add(new Pos(ny,nx,now.t+1));
								cnt++;
							}

						}
						

					}
				}
			}
			
			
			
		}
		return cnt;

	}
	//bit : 연결하려는 다음 파이프 비트 조합 상태, dir : 현재 확인해야하는 방향 비트 
	private static boolean check(int bit, int dir) { // // dir : 3 상 ,2 하,1 좌, 0 우
		switch (dir) {
			case 0:
				//좌 연결되야함
				if((bit & (1<<1)) > 0) return true;
				break;
			case 1:
				//우 연결되야함
				if((bit & (1<<0)) > 0) return true;
				break;
			case 2:
				//상 연결되야함
				if((bit & (1<<3)) > 0) return true;
				break;
			case 3:
				//하 연결되야함
				if((bit & (1<<2)) > 0) return true;
				break;
		}
		
		return false;
	}

}
