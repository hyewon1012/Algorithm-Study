package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1227_미로2 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = 10;
		
		for (int i = 1; i <= TC; i++) {
			int t = Integer.parseInt(br.readLine());
			map = new int[100][100];
			visited = new boolean[100][100];
			//입력
			for (int y = 0; y < 100; y++) {
				String s = br.readLine();
				for (int x = 0; x < 100; x++) {
					int val = s.charAt(x)-'0';
					map[y][x] = val;
				}
			}
			ans = 0;
			//DFS solve
			//solve(1,1);
			//BFS solve
			byBFS(1,1);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		}// end of test case
		System.out.print(sb);
		
	}// end of main
	
	private static void solve(int i, int j) {
		if(map[i][j] == 3) {
			ans = 1;
			return;
		}
		visited[i][j] = true;
		for (int k = 0; k < 4; k++) {
			int ny = i + dy[k];
			int nx = j + dx[k];
			if(ny >= 100 || ny < 0 || nx >= 100 || nx < 0) continue;
			if(map[ny][nx] != 1 && !visited[ny][nx]) {
				visited[ny][nx] = true;
				solve(ny,nx);
				visited[ny][nx] = false;
			}
		}

	}
	private static void byBFS(int y, int x) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(y,x));
		visited[y][x] = true;
		int step = 0;
		while(!q.isEmpty()) {
			int size = q.size(); //현재 큐에 있는 요소의 크기를 확인하고 이 크기만큼만 반복돌기
			while(size-- > 0) {
				Point now = q.poll();
				for (int d = 0; d < 4; d++) {
					int ny = now.r + dy[d];
					int nx = now.c + dx[d];
					//범위 내부?
					if(ny >= 100 || ny < 0 || nx >= 100 || nx < 0) continue;
					//갈수있고 방문안했는지 체크
					if(map[ny][nx] == 0 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						q.add(new Point(ny, nx));
					}else if(map[ny][nx] == 3){ // 목적지 도달
						ans = 1;
						break;
					}
				}
			}//하나의 step 탐색 완료
			step++;
		}
		System.out.println("최종 step : " + step);
	}
	
	private static void byDFS(int y, int x) {
		visited[y][x] = true;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny >= 100 || ny < 0 || nx >= 100 || nx < 0) continue;
			//갈수있고 방문안했는지 체크
			if(map[ny][nx] == 0 && !visited[ny][nx]) {
				byDFS(ny,nx);
			}else if(map[ny][nx] == 3){ // 목적지 도달
				ans = 1;
				return;
			}
		}
	}
	
	static class Point{
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
	}

}
