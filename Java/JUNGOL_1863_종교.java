package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1863_종교 {
	static int N,M;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(x,y);
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if(i==parents[i]) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}
	
	static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(parents[x] == x) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	//x집합에 y집합 흡수 (대표자 업데이트)
	static boolean union(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		if(xRoot == yRoot) return false;
		parents[yRoot] = xRoot;
		return true;
	}

}
