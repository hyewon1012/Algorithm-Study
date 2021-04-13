package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	static int N,X;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken()); // 활주로길이
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0;
			for (int i = 0; i < N; i++) {
				if(solve(i,1)) {
					System.out.println(i+" 가로 경사로설치");
					ans++;
				}
				if(solve(i,0)) {
					System.out.println(i+" 세로 경사로설치");
					ans++;
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);

	}
	
	private static boolean solve(int idx, int flag) {
		int diff = 0; // 다음칸 - 앞칸 높이차이
		int road = 1; // 내가 놓는 경사로 개수

		//flag 1 이면 가로 검사
		//flag 0 이면 세로 검사
		for (int i = 1; i < N; i++) {
			diff = flag == 1 ? map[idx][i] - map[idx][i-1] : map[i][idx] - map[i-1][idx];
			
			if(diff == 0) { //평지일때
				road++;
			}else if(diff == 1) {//올라가는 경사로
				if(road >= X) {
					road = 1;
				}
			}else if(diff == -1) {//내려가는 경사로
				if(road >= 0) {
					road = -X+1;
				}
			}else { // 경사로 놓지 못할땐 바로 리턴
				return false;
			}
		}
		if(road >= 0) return true;
		
		return false;
	}
	
	private static int process() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if(makeRoadByRow(i)) count++;
			if(makeRoadByCol(i)) count++;
		}
		return count;
	}
	
	private static boolean makeRoadByRow(int i) {
		int beforeHeight = map[i][0], size = 0;
		int j = 0; //탐색 열 위치
		
		while(j < N) {
			if(beforeHeight == map[i][j]) {
				++size;
				++j;
			}else if(beforeHeight+1 == map[i][j]) { //오르막 경사로 놓아야 할 때
				if(size < X) return false; // 경사로 설치 불가
				beforeHeight++;
				size = 1;
				++j;
			}else if(beforeHeight-1 == map[i][j]) { // 내리막 경사로 놓아야 할 때
				int cnt = 0;
				for (int k = j; k < N; k++) {
					if(map[i][k] != beforeHeight-1) break;
					if(++cnt == X) break;
				}
				if(cnt < X) return false;
				beforeHeight--;
				size = 0;
				j+=X;
			}else {
				return false;
			}
			
		}
		return true;
	}
	
	private static boolean makeRoadByCol(int i) {
		int beforeHeight = map[0][i], size = 0;
		int j = 0; //탐색 행 위치
		
		while(j < N) {
			if(beforeHeight == map[j][i]) {
				++size;
				++j;
			}else if(beforeHeight+1 == map[j][i]) { //오르막 경사로 놓아야 할 때
				if(size < X) return false; // 경사로 설치 불가
				beforeHeight++;
				size = 1;
				++j;
			}else if(beforeHeight-1 == map[j][i]) { // 내리막 경사로 놓아야 할 때
				int cnt = 0;
				for (int k = j; k < N; k++) {
					if(map[k][i] != beforeHeight-1) break;
					if(++cnt == X) break;
				}
				if(cnt < X) return false;
				beforeHeight--;
				size = 0;
				j+=X;
			}else {
				return false;
			}
			
		}
		return true;
	}
}
