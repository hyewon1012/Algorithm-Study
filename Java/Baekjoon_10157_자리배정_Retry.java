package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * K 1억까지 -> 5 ≤ C, R ≤ 1,000 1억까지안돈다
 * */
public class Baekjoon_10157_자리배정_Retry {
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1,0,-1,0}; // 아래 오른 위 왼
	static int[] dc = {0,1,0,-1}; // 아래 오른 위 왼
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		int W = Integer.parseInt(init[0]);//가로
		int H = Integer.parseInt(init[1]);//세로
		int K = Integer.parseInt(br.readLine());
		
		map = new int[H][W];
		visited = new boolean[H][W];
		if(W*H < K) {
			System.out.println(0);
		}else {
			int num=1;
			int x = 0, y = H-1;
			int R = H;
			int C = W;
			while(num < K) {
				for (int i = 0; i < R; i++) {
					
				}
				num++;
			}

			System.out.println((x+1) + " " + (y+1));

		}
	}
	

}
