package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방 {
/**
 * 깊이우선탐색
 * 이동할수있는 방의 개수가 동일한것이 여러개면 가장 작은 방 번호 출력
*/
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	static int[][] map;
	static int ans;
	static int startRoom;
	//시작점부터 최대한 많은 거리를 도착했을때 내가 이동한 거리를 리턴 
	public static void dfs(int y, int x, int startVal, int[][] map, int cnt) {
		
		for (int k = 0; k < 4; k++) {
			int ny = y + dy[k];
			int nx = x + dx[k];
			
			if(ny >= 0 && ny < map.length && nx >= 0 && nx < map.length) {
				if(map[ny][nx] == map[y][x]+1) {
					dfs(ny,nx, startVal, map, ++cnt);
				}	
			}
		}
		
		if(ans < cnt) {
			ans = cnt;
			startRoom = startVal;
		}
		//같은게 여러개일때
		if(ans == cnt) {
			if(startRoom > startVal) startRoom = startVal;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			//입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//solve
			startRoom = map[0][0];
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i,j,map[i][j],map,1);
				}
			}
			System.out.println("#"+t+" " +startRoom + " " +ans);
			
			
		}

	}

}
