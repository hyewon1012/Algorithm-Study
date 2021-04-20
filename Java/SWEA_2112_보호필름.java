package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름 {
	static int D,W,K;
	static int[][] map;
	
	static int[] candidate; // 각 row별 약품 투약 상태 리스트 (-1 : 투여안함,  0: a 투여,  1 : b 투여)
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			candidate = new int[D];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			ans = Integer.MAX_VALUE;
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int row, int cnt) {
		//내가 구한 최소값보다 더 크면 더이상 탐색하지 않음
		if(cnt >= ans) return;
		//맨 마지막에 도달하면 모든 열 연속되있는지 확인하고 답 찾기
		if(row == D) {
			if(isContinue()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		
		for (int i = -1; i < 2; i++) {
			candidate[row] = i;
			if(i == -1) {
				dfs(row+1, cnt);
			}else {
				dfs(row+1, cnt+1);
			}
		}
	}

	//D개중 1개 2개...D개 모두 약품 투입해보기
	private static void solve(int tryBit) {
		List<Integer> candidate = new ArrayList<>(); // 약품 투입할 행 리스트
		for (int i = 0; i < tryBit; i++) {
			for (int j = 0; j < D; j++) {
				if((i & (1<<j)) > 0) {
					candidate.add(j);
				}
			}
			int cnt = candidate.size();
			
//			for (int j = 0; j < cnt; j++) {
//				System.out.print(candidate.get(j)+" ");
//			}
//			System.out.println();
			
			if(ans == 0) return; // 더 이상 탐색하지 않음
			
			//copy();
			
			if(isContinue()) {
				if(ans > cnt) ans = cnt;
			}else {
				change(candidate);
				if(isContinue()) {
					if(ans > cnt) ans = cnt;
				}
				
			}
			
			candidate.clear();
		}
		
	}
	
	// candidate에 있는 열 특성 모두 바꿈 3개면 000 0이 A 1이 B 001~111
	// 모든 열 K만큼 연속하는지 확인
	//candidate에 있는 번호 행 A,B 바꾸는 모든 경우
	private static void change(List<Integer> candidate) {
		
		int size = candidate.size();
		int tryBit = 1<<size;
		
		if(size == 0) return;
		
		List<Integer> change = new LinkedList<>();
		for (int i = 0; i < tryBit; i++) {
			for (int j = 0; j < size; j++) {
				change.add((i&(1<<j)));
			}
			for (int j = 0; j < size; j++) {
				System.out.println(change.get(j)+" ");
			}
			for (int j = 0; j < size; j++) {
				int rownum = candidate.get(j);
				for (int x = 0; x < D; x++) {
					//copymap[rownum][x] = change.get(j);
				}
			}
		}
		
		
		
		
	}
	
	private static boolean isContinue() {
		int now,next;
		for (int x = 0; x < W; x++) {
			int cnt = 1; //연속하는원소 카운트
			for (int y = 0; y < D-1; y++) {
				now = candidate[y] == -1 ? map[y][x] : candidate[y];
				next = candidate[y+1] == -1 ? map[y+1][x] : candidate[y+1];
				if(now == next) {
					cnt++;
					if(cnt >= K) break;
					
				}
				else {
					cnt=1;
				}
				
			}
			if(cnt < K) return false;

		}
		
		return true;
	}
	

}
