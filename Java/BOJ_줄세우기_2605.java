package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_줄세우기_2605 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] students = new int[N]; // 학생들이 뽑은 번호표
		List<Integer> order = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}
		
		order.add(1);
		
		for (int i = 1; i < N; i++) {
			int size = order.size();
			int num = students[i];
			int location = size-num;
			order.add(location, i+1);
		}
		for (Integer i : order) {
			System.out.print(i+" ");
		}
	}

}
