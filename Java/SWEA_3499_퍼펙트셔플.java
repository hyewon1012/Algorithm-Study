package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_3499_퍼펙트셔플 {
	static Queue<String> left;
	static Queue<String> right;
	static Queue<String> result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC =Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			int N =Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			result = new LinkedList<String>();
			left = new LinkedList<String>();
			right = new LinkedList<String>();
			if(N%2==0) {
				for (int i = 0; i < N/2; i++) {
					left.add(st.nextToken());
				}
				for (int i = N/2; i < N; i++) {
					right.add(st.nextToken());
				}
			}else {
				for (int i = 0; i <= N/2; i++) {
					left.add(st.nextToken());
				}
				for (int i = N/2+1; i < N; i++) {
					right.add(st.nextToken());
				}
			}
			
			while(result.size() < N) {
				result.add(left.poll());
				result.add(right.poll());
			}
			
			
			sb.append("#").append(t).append(" ");
			for (int i = 0; i < N; i++) {
				sb.append(result.poll()).append(" ");
			}
			sb.append("\n");
			
		}
		System.out.print(sb);


	}

}
