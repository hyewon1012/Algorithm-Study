package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Coordinate {
	int y;
	int x;
	int s; // 고슴도치 1 물 0
	public Coordinate(int y, int x, int s) {
		this.y = y;
		this.x = x;
		this.s = s;
	}
}

public class Baekjoon_3055_탈출 {
	/**
	 * 평지 : .
	 * 물 : * 
	 * 돌 : X 
	 * 굴 : D
	 * 고슴도치 : S
	 * 물,고슴도치는 돌을 통과할수없다.
	 * 물은 비버 소굴로 이동할수없다.
	 * 고슴도치 물이 찰 예정인곳으로 이동할수없음.
	 * bfs -> 물 먼저 이동시키고 고슴도치 이동가능한지 판단해준다. -> 물좌표, 고슴도치 좌표 별개로 필요
	 **/
	static Queue<Coordinate> q;
	static char[][] map;
	static int R;
	static int C;
	static int[][] dist;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		R = Integer.parseInt(init[0]);
		C = Integer.parseInt(init[1]);
		
		map = new char[R][C];
		dist = new int[R][C];
		
		q = new LinkedList<>();
		int sy=0,sx=0;
		for (int i = 0; i < R; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = s[j].charAt(0);
				if(map[i][j] == '*') {
					q.add(new Coordinate(i, j, 0));
					dist[i][j] = 0; //물 : 0 , 고슴도치 : 1
				}
				if(map[i][j] == 'S') {
					sy = i;
					sx = j;
				}
			}
		}
		q.add(new Coordinate(sy, sx, 1)); // 고슴도치위치를 큐 마지막에 넣는다.
		dist[sy][sx] = 1;
		bfs();
		
	}
	private static void bfs() {
		while(!q.isEmpty()) {
			Coordinate c = q.poll();
			int y = c.y;
			int x = c.x;
			int s = c.s;
			for (int k = 0; k < 4; k++) {
				int ny = y + dy[k];
				int nx = x + dx[k];
				//범위밖이면 이동 불가능
				if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
				//돌이거나 이미 갔던곳이면 이동 불가능
				if(map[ny][nx] == 'X' || dist[ny][nx] != 0) continue;
				//고슴도치 굴
				if(map[ny][nx] == 'D') {
					//물 이동 불가능
					if(s == 0) {
						continue;
					}
					//고슴도치 도착 끝!!
					System.out.println(dist[y][x]);
					return;
				}
				dist[ny][nx] = dist[y][x] + 1;
				q.add(new Coordinate(ny, nx, s));
				
			}
		}
		System.out.println("KAKTUS");
	}

}
