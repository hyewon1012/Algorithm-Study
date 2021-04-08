package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {
	static int N,M;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			StringBuilder result = new StringBuilder();
			make();
			for (int i = 0; i < M; i++) {
				st  = new StringTokenizer(br.readLine(), " ");
				int order = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(order == 0) { // 합집합
					union(x, y);
				}else { // 같은 집합인지 확인
					int xRoot = findSet(x);
					int yRoot = findSet(y);
					if(xRoot == yRoot) {
						result.append(1);
					}else {
						result.append(0);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
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
	
	//x집합에 y집합 흡수 -> 대표자 업데이트
	static boolean union(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);
		if(xRoot == yRoot) return false;
		parents[yRoot] = xRoot;
		return true;
	}

}
