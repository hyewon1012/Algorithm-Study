package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_10163_색종이 {
	static int[][] map;
	static boolean[][] visited;
	static List<Integer[]> info;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[101][101];
		visited = new boolean[101][101];
		info = new ArrayList<Integer[]>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			Integer[] temp = {y, x, y+height, x+width};
			info.add(temp);
		}
		
		int[] result = new int[N];
		
		for (int i = info.size()-1; i >= 0; i--) {
			Integer[] temp = info.get(i);
			int total=0;
			for (int y = temp[0]; y < temp[2]; y++) {
				for (int x = temp[1]; x < temp[3]; x++) {
					if(!visited[y][x]) {
						visited[y][x] = true;
						total++;
					}
				}
			}
			result[--N] = total;
		}
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

}
