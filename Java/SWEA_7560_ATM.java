package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_7560_ATM {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1,o2)->o1-o2);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int time = Integer.parseInt(st.nextToken());
			pq.add(time);
		}
		
		int total = 0;
		int ans = 0;
		while(!pq.isEmpty()) {
			int val = pq.poll();
			total += val;
			ans += total;
		}
		System.out.println(ans);

	}

}
