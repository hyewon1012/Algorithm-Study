package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793_오나의여신님 {
	static int N,M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static class Pos{
		int y,x;
		boolean isDevil;

		public Pos(int y, int x, boolean isDevil) {
			super();
			this.y = y;
			this.x = x;
			this.isDevil = isDevil;
		}

		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + ", isDevil=" + isDevil + "]";
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			int sy=0,sx=0; // 수연 좌표
			int ty=0,tx=0; // 악마좌표
			
			Queue<Pos> q = new LinkedList<Pos>();
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					
					if(map[i][j] == 'S') {//수연이 위치
						sy = i; sx = j;
					}
					if(map[i][j] == '*') { //악마위치
						ty = i; tx = j;
					}
				}
			}
			//bfs
			int ans = Integer.MAX_VALUE;
			int turn = 1;
			q.offer(new Pos(ty, tx, true));
			q.offer(new Pos(sy, sx, false));
go:			while(!q.isEmpty()) {
				int size = q.size();
				while(size-->0) {
					Pos now = q.poll();
					for (int d = 0; d < 4; d++) {
						int ny = now.y + dy[d];
						int nx = now.x + dx[d];
						if(ny >= N || ny < 0 || nx >= M || nx < 0) continue;
						if(now.isDevil) { // 악마라면 빈곳이랑 수연이 위치로 이동 가능
							if(map[ny][nx] == '.' || map[ny][nx] == 'S') {
								map[ny][nx] = '*'; //방문처리
								q.offer(new Pos(ny, nx, true));
							}
						}else {
							if(map[ny][nx] == '.') {
								map[ny][nx] = 'S'; //방문처리
								q.offer(new Pos(ny, nx, false));
							}
							if(map[ny][nx] == 'D') { // 여신만나면 종료
								ans = turn;
								break go;
							}
						}

					}
				}
				turn++;
				
			}// end BFS
			
			sb.append("#").append(t).append(" ").append(ans==Integer.MAX_VALUE?"GAME OVER":ans).append("\n");
		}
		System.out.print(sb);

	}
	/*
	static void bfs() {
		int turn = 1;
		while (!points.isEmpty()) {
			// 초마다 현재 queue 사용하기...
			int size = points.size();
			while (size-- > 0) {
				Point head = points.poll();

				// 자식 탐색 한다.
				for (int d = 0; d < deltas.length; d++) {
					int nr = head.r + deltas[d][0];
					int nc = head.c + deltas[d][1];

					if (isIn(nr, nc)) {
						// 지금 녀석이 악마라면.. 다음으로 이동은 .과 S
						if (head.isDevil) {
							if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
								map[nr][nc] = '*'; // 방문처리
								points.offer(new Point(nr, nc, true));
							}
						}
						// 지금 녀석이 수연이라면.. . 또는 D (여신 - 만나면 종료)
						else {
							if (map[nr][nc] == 'D') {
								A = turn;
								return;
							} else if (map[nr][nc] == '.') {
								map[nr][nc] = 'S';
								points.offer(new Point(nr, nc, false));
							}
						}
					}
				}
			}
			turn++;
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	*/

}
