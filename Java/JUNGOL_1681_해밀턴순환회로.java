package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUNGOL_1681_해밀턴순환회로 {
	
	static class Node {
		int node, weight;
		
		public Node(int node, int weight) {
			super();
			this.node = node;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [node=" + node + ", weight=" + weight + "]";
		}
		
	}
	static int N;
	static ArrayList<Node>[] adjList;
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Node>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(cost == 0) continue;
				adjList[i].add(new Node(j,cost));
			}
		}
		//System.out.println(Arrays.deepToString(adjList));
		ans = Integer.MAX_VALUE;
		solve(0,0,0);
		System.out.println(ans);
		
		
	}
	private static void solve(int now, int depth, int cost) {
		if(cost >= ans) return; // 이 리턴조건 추가해야 시간초과 해결!!!
		
		if(now == 0 && depth == N) {	
			ans = Math.min(ans, cost);
			return;
		}
		
		for (int i = 0; i < adjList[now].size(); i++) {
			Node next = adjList[now].get(i);
			if(!visited[next.node]) {
				visited[next.node] = true;
				solve(next.node, depth+1, cost + next.weight);
				visited[next.node] = false;
			}
			
		}
		
	}

}
