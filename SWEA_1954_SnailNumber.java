package com.ssafy.off;

import java.util.Scanner;

public class SWEA_1954_SnailNumber {

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
			
			while(ele<=N*N) {
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
