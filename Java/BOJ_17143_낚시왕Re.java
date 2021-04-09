package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕Re {
	static int R,C,M;
	static Shark[][] map;
	static int[] dy = {-1,1,0,0};//위 아래 오른 왼
	static int[] dx = {0,0,1,-1};//위 아래 오른 왼
	
	static class Shark{
		int y,x,s,d,z;
		
		public Shark(int y, int x, int s, int d, int z) {
			super();
			this.y = y;
			this.x = x;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		public void move(int y, int x, int s) {
			int ny = y;
			int nx = x;
			for (int i = 0; i < s; i++) {
				ny += dy[this.d];
				nx += dx[this.d];
				if(ny == -1) {
					ny = 1;
					d = 1;
				}else if (ny == R) {
					ny = R-2;
					d = 0;
				}
				if(nx == -1) {
					nx = 1;
					d = 2;
				}else if(nx == C) {
					nx = C-2;
					d = 3;
				}
			}
			
			this.y = ny;
			this.x = nx;
		}
		@Override
		public String toString() {
			return "Shark [y=" + y + ", x=" + x + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[100][100];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(y, x, s, d, z);
			map[y][x] = shark;
			
		}

		int cnt = 0;
		Shark backup[][] = new Shark[100][100];
		for (int t = 0; t < C; t++) {
			for (int i = 0; i < R; i++) {
				if(map[i][t] != null) {
					cnt += map[i][t].z;
					map[i][t] = null;
					break;
				}
			}
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					backup[i][j] = map[i][j];
					map[i][j] = null;
				}
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					Shark now = backup[i][j];
					if(now != null) {
						int y = now.y;
						int x = now.x;
						int s = now.s;
						now.move(y, x, s);
						int ny = now.y;
						int nx = now.x;
						if(map[ny][nx] == null || (map[ny][nx] != null && map[ny][nx].z < now.z)) {
							map[ny][nx] = now;
						}
					}
				}
			}
		}
		
		System.out.println(cnt);
		
	}


}
