package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 연속적으로 증가하거나 감소하는 최장길이
 * 
 * */
public class Baekjoon_2491_수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Stack<Integer> plus = new Stack<Integer>();
		Stack<Integer> minus = new Stack<Integer>();
		//증가 찾기
		int maxLength = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if(plus.isEmpty()) {
				plus.add(arr[i]);
			}else {
				if(plus.peek() <= arr[i]) {
					plus.add(arr[i]);
				}else {
					maxLength = Math.max(maxLength, plus.size());
					plus.clear();
					plus.add(arr[i]);
				}
			}
		}
		maxLength = Math.max(maxLength, plus.size());
		
		//감소 찾기
		int minLength = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if(minus.isEmpty()) {
				minus.add(arr[i]);
			}else {
				if(minus.peek() >= arr[i]) {
					minus.add(arr[i]);
				}else {
					minLength = Math.max(minLength, minus.size());
					minus.clear();
					minus.add(arr[i]);
				}
			}
		}
		minLength = Math.max(minLength, minus.size());
		
		int ans = Math.max(maxLength, minLength);
		System.out.println(ans);
	}
	
}
