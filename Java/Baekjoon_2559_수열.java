package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 연속적인 며칠동안의 온도의 합이 가장 큰지? 이때 합 찾기
 * 1 <= K <= N
 * */
public class Baekjoon_2559_수열 {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		int N = Integer.parseInt(init[0]);
		int K = Integer.parseInt(init[1]);
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int ans = Integer.MIN_VALUE;
		for (int j = 0; j <= N-K; j++) {
			ans = Math.max(ans, getSum(j,K));
		}
		System.out.println(ans);

	}
	//start~start+day 까지 구간의 합 리턴
	private static int getSum(int start, int day) {
		int sum = 0;
		for (int i = start; i < start+day; i++) {
			sum += arr[i];
		}
		return sum;
	}
}
