package com.ssafy.off;

import java.util.Scanner;

public class SWEA_1954_SnailNumber {
	
	public static void solve(int N, int[][] map) {
		int cnt = 1; //배열 각 칸에 넣을 숫자
		int dir = 1; //진행방향값 1 or -1
		int y = 0, x = -1; //시작점
		int size = N*N;
		while(cnt <= size) {
			//우->하->좌->상
			
			//우 : x가 증가, 좌 : x가 감소 
			for (int k = 0; k < N; k++) {
				x += dir;
				map[y][x] = cnt;
				cnt++;
			}
			
			N--; // 우->하, 좌->상
			
			//하 : y가 증가, 상 : y가 감소
			for (int k = 0; k < N; k++) {
				y += dir;
				map[y][x] = cnt;
				cnt++;
			}
			dir*=(-1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int[][] map;
		for (int t = 1; t <=tc; t++) {
			int N = sc.nextInt();
			//solve
			map = new int[N][N];
			int y = 0;
			int x = 0;
			int ele = 1;
			int rowSize = N;
			int colSize = N-1;
			/*
			while(ele <= N*N) {
				//오른쪽방향
				for (int i = 0; i < rowSize; i++) {
					map[y][x++] = ele++;
				}
				--rowSize;
				++y;
				--x;
				//위->아래
				for (int i = 0; i < colSize; i++) {
					map[y++][x] = ele++; 
				}
				--colSize;
				--y;
				--x;
				//왼쪽방향
				for (int i = 0; i < rowSize; i++) {
					map[y][x--] = ele++;
				}
				--rowSize;
				--y;
				++x;
				//아래 -> 위
				for (int i = 0; i < colSize; i++) {
					map[y--][x] = ele++;
				}
				--colSize;
				++y;
				++x;
			}
			*/
			solve(N,map);
			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
				
			}

		}
		
		

	}

}
