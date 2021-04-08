package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Baekjoon_1987_알파벳 {
/**
 * next[key]의 value가 1일때 이미 지나간것.
 * 갈수있는게 여러개면 다시 돌아와서 탐색
 * 
 * */
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int R,C;
	static HashMap<Character, Boolean> alphabet;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		
		R = Integer.parseInt(init[0]);
		C = Integer.parseInt(init[1]);
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = c[j];
			}
		}
		
		alphabet = new HashMap<Character, Boolean>();
		for (int i = 65; i <= 90; i++) {
			alphabet.put((char)i, false);
		}
		
		
		System.out.println(dfs(0,0,0));
		
	}

	public static int dfs(int y, int x, int cnt) {
//		if(alphabet.get(map[y][x])) {
//			System.out.println(y+" , "+x);
//			ans = Math.max(ans, cnt);
//			return;
//		}else {
//			alphabet.put(map[y][x], true);
//		}
		int result = cnt;
		if(visited[y][x] || alphabet.get(map[y][x])) {
			return cnt;
		}
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;

			if(!visited[ny][nx] && !alphabet.get(map[ny][nx])) {
				visited[ny][nx] = true;
				alphabet.put((char)map[ny][nx], true);
				
				result = Math.max(result, dfs(ny,nx,cnt++));
				
				visited[ny][nx] = false;
				alphabet.put((char)map[ny][nx], false);
				
			}
			
		}
		
		return result;
	}

}
