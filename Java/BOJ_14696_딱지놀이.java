package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14696_딱지놀이 {
	static int[][] A;
	static int[][] B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 총 라운드 수
		A = new int[N][5];
		B = new int[N][5];
		//별 동그라미 네모 세모 4 3 2 1
go:		for (int i = 0; i < N; i++) {
			//a 정보 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // a가 n번 라운드에서 가지고 있는 그림 총 개수
			for (int j = 0; j < a; j++) {
				int cardNum = Integer.parseInt(st.nextToken()); 
				A[i][cardNum] += 1;
			}
			//b 정보 입력
			st = new StringTokenizer(br.readLine(), " ");
			int b = Integer.parseInt(st.nextToken()); // a가 n번 라운드에서 가지고 있는 그림 총 개수
			for (int j = 0; j < b; j++) {
				int cardNum = Integer.parseInt(st.nextToken()); 
				B[i][cardNum] += 1;
			}
			//라운드별 승부 계산
			for (int j = 4; j > 0; j--) {
				if(A[i][j] > B[i][j]) {
					sb.append("A").append("\n");
					continue go;
				}else if(A[i][j] < B[i][j]) {
					sb.append("B").append("\n");
					continue go;
				}
			}
			sb.append("D").append("\n");
		}
		System.out.print(sb);
		
	}//end of main

}
