package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_2304_창고다각형 {
	static int N;
	static int[] h;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		h = new int[1001];
		
		int maxIdx = 1;
		int startIdx = Integer.MAX_VALUE;
		int endIdx = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			startIdx = Math.min(startIdx, x); // 시작 x좌표값
			endIdx = Math.max(endIdx, x);//마지막 x좌표값
			h[x] = y;
			
			if(h[maxIdx] < h[x]) {
				maxIdx = x; //최대높이를 가진 x좌표값
			}
		}
		int left = left(startIdx, maxIdx);
		int middle = h[maxIdx];
		int right = right(endIdx, maxIdx);
		
		System.out.println(left+middle+right);
	}
	
	//오른쪽영역
	private static int right(int start, int mid) {
		int sum = 0;
		int height = 0;
		for (int i = start; i > mid; i--) {
			height = Math.max(height, h[i]);
			sum += height;
		}
		
		return sum;
	}
	//왼쪽영역
	private static int left(int start, int mid) {
		int sum=0;
		int height = 0;
		for (int i = start; i < mid; i++) {
			height = Math.max(height, h[i]);
			sum += height;
		}

		return sum;
	}

}
