package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트 {
	/**
	 * 가장 선호하는 햄버거 조합 && 칼로리 이하
	 * (같은 재료를 여러번 사용할수없다.) 
	 */
	static int N;
	static int Kalorie;
	static int answer;
	static int info[][];
	
	private static int find(int idx, int score, int remain) {
		if( remain < 0 ) return 0; // 조합 성립 안됨
		if( remain == 0) return score;
		if( idx == N ) return score; // 모든 재료를 조합 만드는데 사용
		
		int v1 = find(idx+1, score, remain); // 그 다음 재료 선택하러감
		int v2 = find(idx+1, score + info[idx][0], remain - info[idx][1]);
		
		return Math.max(v1, v2);
	}
	
	public static int solve(int idx, int score, int kalorie) {
		if (idx == N || kalorie == Kalorie) {
			return score;
		}
		else {
			//idx 선택할수있음
			if(kalorie + info[idx][1] <= Kalorie) {
				answer = Math.max(solve(idx+1, score + info[idx][0], kalorie + info[idx][1]), solve(idx+1, score, kalorie));
			}else {
				answer = solve(idx+1, score, kalorie);
			}
		}
		return answer;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			
			String[] data = br.readLine().split(" ");
			
			N = Integer.parseInt(data[0]);
			Kalorie = Integer.parseInt(data[1]);
			info = new int[N][2]; //맛(0),칼로리(1) 정보 배열
			answer = -1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				info[i][0] = Integer.parseInt(st.nextToken()); 
				info[i][1] = Integer.parseInt(st.nextToken());
			}
			answer = solve(0, 0, 0);
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);

	}

}
