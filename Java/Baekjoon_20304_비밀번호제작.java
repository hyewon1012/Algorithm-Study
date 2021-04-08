package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_20304_비밀번호제작 {
	static int N,M,result = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 관리자 계정 최대값
		M = Integer.parseInt(br.readLine()); // 해커가 시도한 패스워드 개수
		int[] attack = new int[M]; // 해커가 사용한 패스워드 리스트
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			attack[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			int safeDistance = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) { // 안전거리 계산
				int tmp = 0; // 서로 다른 비트 수 기록한 변수
				tmp = countDiffBit(i, attack[j]);
				safeDistance = Math.min(safeDistance, tmp);
			}
			result = Math.max(safeDistance, result);
		}
		System.out.println(result);
	}
	//사용 가능한 패스워드 후보와 해커가 사용한 패스워드를 비교하면서 안전거리 계산하기
	private static int countDiffBit(int admin, int hacker) {
		int tmp = admin ^ hacker; // 정수형으로 쓸때 비트 단위로 연산을 한다. -> 서로 다른 비트만 1로 나옴.
		int cnt = 0;
		
		char[] s = Integer.toBinaryString(tmp).toCharArray();
		
		for (int i = 0; i < s.length; i++) {
			if(s[i] == '1') {
				cnt++;
			}
		}
		
		return cnt;
	}
	
}
