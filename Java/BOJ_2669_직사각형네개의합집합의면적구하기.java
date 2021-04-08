package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2669_직사각형네개의합집합의면적구하기 {
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[101][101];
		visited = new boolean[101][101];
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int leftX = Integer.parseInt(st.nextToken());
			int leftY = Integer.parseInt(st.nextToken());
			int rightX = Integer.parseInt(st.nextToken());
			int rightY = Integer.parseInt(st.nextToken());
			
			for (int x = leftX; x < rightX; x++) {
				for (int y = leftY; y < rightY; y++) {
					if(!visited[x][y]) {
						visited[x][y] = true;
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
		
	}

}
