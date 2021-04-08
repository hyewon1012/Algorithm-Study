package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {
	static int N; // 편의점개수
	static List<Pos> graph;
	static boolean[][] connected;
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new ArrayList<>();
			//집,편의점,페스티벌
			for (int i = 0; i < N+2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph.add(new Pos(x,y));
			}
			connected = new boolean[N+2][N+2];
			
			//1000m이하 두 정점
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					if(i==j) continue;
					if(Math.abs(graph.get(i).x - graph.get(j).x)+Math.abs(graph.get(i).y - graph.get(j).y) <= 1000) {
						connected[i][j] = true;
						connected[j][i] = true;
					}
				}
			}
			
			//플로이드
			for (int k = 0; k < N+2; k++) {
				for (int i = 0; i < N+2; i++) {
					for (int j = 0; j < N+2; j++) {
						if(connected[i][k] && connected[k][j]) {
							connected[i][j] = true;
						}
					}
				}
			}
			String ans = connected[0][N+1] ? "happy" : "sad";
			sb.append(ans).append("\n");
		}// end of test case
		System.out.println(sb);
	}

}
