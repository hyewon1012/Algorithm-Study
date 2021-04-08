package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * K 1억까지 -> 5 ≤ C, R ≤ 1,000 1억까지안돈다
 * */
public class Baekjoon_10157_자리배정 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1,0,-1,0}; // 아래 오른 위 왼
	static int[] dc = {0,1,0,-1}; // 아래 오른 위 왼
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		int C = Integer.parseInt(init[0]);//가로
		int R = Integer.parseInt(init[1]);//세로
		int K = Integer.parseInt(br.readLine());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		if(C*R < K) {
			System.out.println(0);
		}else {
			int num=1;
			int r = 0, c = 0;
			
			int nd = 0;
			int nr=0,nc=0;
			
			while(num < K) {
				map[r][c] = num;
				visited[r][c] = true;
				nr = r + dr[nd];
				nc = c + dc[nd];
				if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc]) {
					nd = (nd+1)%4;
					nr = r + dr[nd];
					nc = c + dc[nd];
				}
				r = nr;
				c = nc;
				num++;
			}

			System.out.println((c+1) + " " + (r+1));

		}
	}
	private static void solve(int R, int C, int K) {
		int r = 0;
		int c = 0;
		int num = 1;
		int last = R*C;

ex:		while(num < last) { // 하 우 상 좌
			for (int i = 1; i < R; i++) {
				if(num == K) {
					System.out.println((c+1) + " " + (r+1));
					break ex;
				}
				map[r][c] = num;
				r++; num++;
			}
			for (int i = 1; i < C; i++) {
				if(num == K) {
					System.out.println((c+1) + " " + (r+1));
					break ex;
				}
				map[r][c] = num;
				c++; num++;
			}
			for (int i = 1; i < R; i++) { // 상
				if(num == K) {
					System.out.println((c+1) + " " + (r+1));
					break ex;
				}
				map[r][c] = num;
				r--; num++;
			}
			for (int i = 1; i < C; i++) { // 좌
				if(num == K) {
					System.out.println((c+1) + " " + (r+1));
					break ex;
				}
				map[r][c] = num;
				c--; num++;
			}
			r++; c++; //안쪽 사이클 시작 위치로 이동
			R -= 2;
			C -= 2;
			
		}
	}

}
