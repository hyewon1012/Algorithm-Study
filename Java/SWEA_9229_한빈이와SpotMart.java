package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 과자 두봉지 최대 무게 합
 * 1. 최대 M그램 이상
 * 2. 과자 한개당 두봉지 사야함.
 */
public class SWEA_9229_한빈이와SpotMart {
	static int answer;
	static int[] arr;
	static int MAX_WEIGHT;
	
	private static int solve(int idx, int cnt, int weight) {
		if(cnt < 2 && idx == arr.length) {
			return -1;
		}

		if(cnt == 2) {
			return weight;
		}
		
		//idx번 선택함
		if(weight+arr[idx] <= MAX_WEIGHT) {
			answer = Math.max(solve(idx+1, cnt, weight), solve(idx+1, cnt+1, weight+arr[idx]));
		}
		//idx번 선택못함
		else {
			answer = solve(idx+1, cnt, weight);	
		}
		
		return answer;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 과자봉지개수
			MAX_WEIGHT = Integer.parseInt(st.nextToken()); //무게 합 제한
			
			arr = new int[N]; // N개 과자 두봉지 무게 배열
			
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			answer = -1;
			int ans = solve(0,0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		} // end of Test Case
		System.out.print(sb);
		
	}// end of main
}// end of class