package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_14889_스타트와링크 {
/**
 * N개중 N/2명 조합으로 뽑는다.
 * 최소값 가장 작은거 출력
 * @throws IOException 
 * @throws NumberFormatException 
 * */
	static int N;
	static int[][] map;
	static int[] teamA;
	static int[] teamB;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		teamA = new int[N/2];
		teamB = new int[N/2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0, N/2); // 두팀으로 나누기 -> teamA, teamB 정해짐
		System.out.println(ans);
		
	}
	private static void comb(int cnt, int start, int total) {
		if(cnt == total) {
			boolean[] isSelected = new boolean[N];
			
			for (int i = 0; i < N/2; i++) {
				isSelected[teamA[i]] = true;
			}
			int j=0;
			for (int i = 0; i < N; i++) {
				if(!isSelected[i]) {
					teamB[j++] = i;
				}
			}
			int sum_a=0, sum_b=0;
			for (int i = 0; i < N/2; i++) {
				for (int k = i+1; k < N/2; k++) {
					if(i==k) continue;
					sum_a += map[teamA[i]][teamA[k]];
					sum_a += map[teamA[k]][teamA[i]];
					
					sum_b += map[teamB[i]][teamB[k]];
					sum_b += map[teamB[k]][teamB[i]];
				}
			}
			ans = Math.min(ans, Math.abs(sum_a-sum_b));
			return;
		}
		for (int i = start; i < N; ++i) {
			teamA[cnt] = i;
			comb(cnt+1, i+1, total);
		}
		
	}

}
