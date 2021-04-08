package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int R,C;
	static int[][] map;
	static int[][] copyMap;
	static boolean[][] visited;
	static List<Virus> virusPos;
	
	static class Virus{
		int y,x;

		public Virus(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,-1,1};
	static int ans;
	static int virusCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		virusPos = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusPos.add(new Virus(i, j));
				}
			}
		}
		ans = Integer.MIN_VALUE;
		setWalls(0);

		System.out.println(ans);
	}
	private static void setWalls(int cnt) {
		if(cnt == 3) {
			//바이러스 퍼뜨리기
			spread();
//			System.out.println("-------------------------");
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j] +" ");
//				}
//				System.out.println();
//			}
			
			int total = 0;
			//바이러스가 최소로 퍼져야 안전영역이 최대
			//안전영역 개수 카운트
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(copyMap[i][j] == 0) {
						total++;
					}
				}
			}
			
			ans = Math.max(ans, total);
			return;
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]==0) {
					map[i][j] = 1;
					setWalls(cnt+1);
					map[i][j] = 0;
				}
			}
		}
		
	}
	private static int spread() {
		Queue<Integer[]> q = new LinkedList<>();
		copyMap = copyMap(map);
		boolean[][] spreadChecked = new boolean[R][C];
		for (int i = 0; i < virusPos.size(); i++) {
			int y = virusPos.get(i).y;
			int x = virusPos.get(i).x;
			Integer temp[] = {y,x};
			q.add(temp);
		}
		int total = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				Integer[] pos = q.poll();
				for (int d = 0; d < 4; d++) {
					int ny = pos[0] + dy[d];
					int nx = pos[1] + dx[d];
					if(ny >= R || ny < 0 || nx >= C || nx < 0) continue;
					if(map[ny][nx] == 0 && !spreadChecked[ny][nx]) {
						spreadChecked[ny][nx] = true;
						copyMap[ny][nx] = 2; // 바이러스 표시
						Integer next[] = {ny,nx};
						q.add(next);
					}
				}
			}
		}
		return total;
	}
	private static int[][] copyMap(int[][] origin) {
		int[][] copy = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
		
	}

}
