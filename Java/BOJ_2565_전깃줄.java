package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2565_전깃줄 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int A[] = new int[501];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int idx = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			A[idx] = val;
		}
		//System.out.println(Arrays.toString(A));
		
		List<Integer> arrA = new ArrayList<Integer>();
		for (int i = 0; i < 501; i++) {
			if(A[i] != 0) {
				arrA.add(A[i]);
			}
		}
		
		int ans = LIS2(arrA);
		System.out.println(N-ans);

	}
	private static int LIS(List<Integer> arr) {
		int max = 0;
		int LIS[] = new int[arr.size()];

		for (int i = 0; i < arr.size(); i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if(arr.get(j) < arr.get(i) && LIS[j] < LIS[j]+1) {
					LIS[i] = LIS[j]+1;
				}
			}
			if(max < LIS[i]) {
				max = LIS[i];
			}
		}
		return max;
	}
	
	private static int LIS2(List<Integer> arr) {
		int size = 0;
		int LIS[] = new int[arr.size()];

		for (int i = 0; i < arr.size(); i++) {
			int temp = Arrays.binarySearch(LIS,0,size, arr.get(i));
			temp = Math.abs(temp)-1;
			LIS[temp] = arr.get(i);
			if(temp == size) ++size;
		}
		return size;
	}

}
