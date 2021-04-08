package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 최소 비용, 최단 거리 -> BFS
 * BFS로 못푸는경우는 가중치가 있을때 -> greedy (다익스트라 : 한 정점에서 다른 모든 정점까지의 최단거리, 플로이드 : 각 정점간 최단거리 + n이 1000개 미만인지)
 * */
public class SWEA_8382_방향전환 {
	static int x1, y1;
	static int endX, endY;
	static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};//세로세로 가로가로
	
	static class Point{
		int r,c;
		boolean isHor; //true : 수평 , false:수직 
		public Point(int r, int c, boolean isHor) {
			super();
			this.r = r;
			this.c = c;
			this.isHor = isHor;
		}
	}
	static Queue<Point> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken())+100;
			y1 = Integer.parseInt(st.nextToken())+100;
			endX = Integer.parseInt(st.nextToken())+100;
			endY = Integer.parseInt(st.nextToken())+100;
			
			sb.append("#").append(t).append(" ").append(bfs()).append("\n");
		} // end of test case
		System.out.print(sb);

	}

	private static int bfs() {
		q = new LinkedList<>();
		q.add(new Point(y1,x1,true)); // 수평 방향
		q.add(new Point(y1,x1,false)); // 수직 방향
		
		boolean [][][] visited = new boolean[201][201][2];
		
		visited[y1][x1][0] = true;
		visited[y1][x1][1] = true;
		
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				//1. 처음 녀석 가져오기
				Point now = q.poll();
				//2. 필요하면 사용하기
				if(now.r == endY && now.c == endX) {
					return time;
				}
				//3. 다음 자식 가보기
				if(now.isHor) {
					for (int d = 0; d < 2; d++) {
						int nr = now.r + deltas[d][0];
						int nc = now.c + deltas[d][1];
						if(isIn(nr,nc) && !visited[nr][nc][1]) {
							visited[nr][nc][1] = true;
							q.add(new Point(nr,nc,false));
						}
					}
					
				}else {
					for (int d = 2; d < 4; d++) {
						int nr = now.r + deltas[d][0];
						int nc = now.c + deltas[d][1];
						if(isIn(nr,nc) && !visited[nr][nc][0]) {
							visited[nr][nc][0] = true;
							q.add(new Point(nr,nc,true));
						}
					}
				}
			}
			time++;
		}
		
		return -1;
	}
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 201 && 0 <= c && c < 201;
	}
}
