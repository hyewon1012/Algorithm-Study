package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961_회전초밥 {
	static int N,d,k,c;
	static int[] sushi;
	static int[] picked;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 회전 초밥 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		sushi = new int[N];
		picked = new int[d+1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine()); 
		}
		
		System.out.println(solve(k));
	}
	
	private static int solve(int size) {

		int cnt = 0;
		int res = 0;
		for (int i = 0; i < k; i++) {
			if(picked[sushi[i]] == 0) res++;
			picked[sushi[i]]++;
		}
		//쿠폰에 적힌게 새로운 종류면
		cnt = res;
		res = (picked[c] == 0) ? cnt+1 : cnt;
		
		//돌면서  슬라이딩 윈도우
		int start = k;
		while(true) {
			//윈도우 왼쪽끝
			picked[sushi[(start-k) % N]]--; // 왼쪽끝초밥 빼고
			if(picked[sushi[(start-k) % N]] == 0) cnt--; //뺐더니 한번도 먹은적이 없음 -> 종류 1감소
			
			//윈도우 오른쪽끝
			if(picked[sushi[start % N]] == 0) cnt++; //안먹어본 초밥
			picked[sushi[start % N]]++;
			
			res = Math.max(res, (picked[c] == 0 ? cnt+1 : cnt));
			
			start++;
			//한바퀴 다 돌았으면 그만
			if(start == (N-1)+k) break;
		}
		return res;
	}

}
