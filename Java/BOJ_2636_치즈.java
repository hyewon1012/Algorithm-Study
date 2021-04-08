package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
치즈가 모두 없어지는데 걸리는시간, 모두 녹기 한시간전 치즈 개수
1. 경계에 있는 치즈 어떻게 찾아줄래? 밖에서부터 거꾸로 보자 map[y][x] == 0 일때 bfs로 찾는다
2. 경계값 좌표 따로 저장해주자. -> step 마다 묶어서 녹여주기 map[y][x] = 0 처리해준다
3. 녹는 치즈 개수 세줘서 [[time,cnt]] List<Integer[]> 2차원배열로 저장해준다.
*/

public class BOJ_2636_치즈 {
	static class Pos {
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + "]";
		}
		
	}
	
	static int R,C;
	static int[][] map;
	static boolean[][] visited;
	static List<Integer[]> area;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		area = new ArrayList<Integer[]>();
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0,0);
		for (int i = 0; i < area.size(); i++) {
			if(area.get(i)[1] == 0) {
				System.out.println(area.get(i-1)[0]);
				System.out.println(area.get(i-1)[1]);
			}
		}
		
	}
	//경계에 있는 치즈 찾아서 갯수 세고, 0 으로 바꿔놓기
	private static void solve(int i, int j) {
		ArrayList<Integer[]> deletePos; // step 마다 녹아야하는 경계 치즈 좌표들
		int cnt; // step 마다 녹는 치즈 개수
		int time = 0;
		while(true) {
			deletePos = new ArrayList<>();
			Queue<Pos> q = new LinkedList<>();
			visited = new boolean[R][C];
			q.add(new Pos(i,j));
			cnt = 0;
			//0 기준 bfs 탐색
			while(!q.isEmpty()) {
				Pos now = q.poll();
				for (int k = 0; k < 4; k++) {
					int ny = now.y + dy[k];
					int nx = now.x + dx[k];
					if(ny >= R || ny < 0 || nx >= C || nx < 0) continue;
					
					if(map[ny][nx] == 0 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						q.add(new Pos(ny,nx));
					}
					if(map[ny][nx] == 1 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						Integer[] temp = {ny,nx};
						deletePos.add(temp); // 경계값 치즈들 -> 0으로 바꿔줘야 할 좌표값들
						cnt++;
					}
					
				}
			}
			//경계 치즈 녹여주기
			for (int k = 0; k < deletePos.size(); k++) {
				int y = deletePos.get(k)[0];
				int x = deletePos.get(k)[1];
				map[y][x] = 0;
			}
			time++;
			Integer[] temp = {time,cnt};
			area.add(temp);
			if(cnt == 0) break;
		}
		
		

	}

}
