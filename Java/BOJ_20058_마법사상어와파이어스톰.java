package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰 {
	static int N,Q;
	static int[] size;
	static int[][] map;
	
	static int[][] temp;
	static int[] dy = {0,1,0,-1}; //오른 아래 왼 위
	static int[] dx = {1,0,-1,0};
	static int cnt; // 남아있는 얼음 합
	static int iceBerg; // 가장 큰 얼음영역 크기
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken()); // 돌리는 횟수
		map = new int[N][N];
		temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		size = new int[Q];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			size[i] = Integer.parseInt(st.nextToken());
		}
		
		iceBerg = Integer.MIN_VALUE;
		solve();
		iceBerg = iceBerg == Integer.MIN_VALUE ? 0 : iceBerg;
		System.out.println(cnt);
		System.out.println(iceBerg);
	}
	
	private static void solve() {
		
		for (int i = 0; i < Q; i++) {
			int length = (int)Math.pow(2, size[i]); // 격자 크기
			
			//시계 방향 90도 회전
			for (int y = 0; y < N; y+=length) {
				for (int x = 0; x < N; x+=length) {
					rotate(y,x,length);
				}
			}
			melt();//(y,x) 주변으로 얼음 3칸인접X or 4칸인접X ->(y,x) 얼음양 1 감소
		}
		
		cnt = 0;
		// 남아있는 얼음 총 합
		// 가장 큰 덩어리가 있는 칸의 개수 BFS 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0) {
					cnt += map[i][j];
					iceBerg = Math.max(iceBerg, findIceBerg(i, j));
				}

			}
		}
		
		
	}
	
	private static void rotate(int y, int x, int size) {
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[i][j] = map[y+size-1-j][x+i];
			}
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[y+i][x+j] = temp[i][j];
			}
		}

	}
	private static void melt() {
		Queue<int[]> meltPos = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				int adjCnt = 0;//인접한얼음 개수
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if(ny >= N || ny < 0 || nx >= N || nx < 0) continue;
					if(map[ny][nx] > 0) {
						adjCnt++;
					}
					
				}
				if(adjCnt < 3) {
					meltPos.add(new int[] {i,j});
				}
			}
		}
		
		while(!meltPos.isEmpty()) {
			int[] pos = meltPos.poll();
			int y = pos[0];
			int x = pos[1];
			map[y][x]--;
		}

	}
	
	//가장 큰 얼음영역 bfs로 찾기
	private static int findIceBerg(int y, int x) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new int[] {y,x});
		visited[y][x] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowY = now[0];
			int nowX = now[1];
			for (int d = 0; d < 4; d++) {
				int ny = nowY + dy[d];
				int nx = nowX + dx[d];
				if(ny >= N || ny < 0 || nx >= N || nx < 0) continue;
				if(map[ny][nx] > 0 && !visited[ny][nx]) {
					cnt++;
					visited[ny][nx] = true;
					q.add(new int[] {ny,nx});
				}
			}
			
		}
		return cnt;
	}
	
	
	private static void printMap() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				System.out.print(map[y][x]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
