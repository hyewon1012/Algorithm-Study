package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1219_길찾기 {
	static int N;
	static class LinkNode {
		int v;
		LinkNode next;
		
		public LinkNode(int v, LinkNode next) {
			super();
			this.v = v;
			this.next = next;
		}
		@Override
		public String toString() {
			return "LinkNode [v=" + v + ", next=" + next + "]";
		}
		
	}
	static LinkNode[] graph;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			st.nextToken();
			N = Integer.parseInt(st.nextToken());
			
			graph = new LinkNode[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i < N; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from] = new LinkNode(to, graph[from]);
			}
			ans = 0;
			bfs();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void dfs(int no, boolean[] visited) {
		if(no == 99) {
			ans = 1;
			return;
		}
		visited[no] = true;
		LinkNode child = graph[no];
		while(child != null) {
			if(!visited[child.v]) {
				dfs(child.v, visited);
			}
			child = child.next;
		}
	}
	
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean [] visited = new boolean[100];
		q.offer(0); // 출발점 고정
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int head = q.poll();
			if(head == 99) {
				ans = 1;
				return;
			}
			LinkNode child = graph[head];
			while(child != null) {
				if(!visited[child.v]) {
					visited[child.v] = true;
					q.offer(child.v);
				}
				child = child.next;
			}	
		}
		
	}

}
