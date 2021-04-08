package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {
	
	static class Node implements Comparable<Node>{
		int to,weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static ArrayList<Node>[] adjList;
	static boolean[] visited;
	static int[] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[u].add(new Node(v,c));
		}
		//System.out.println(Arrays.deepToString(adjList));
		distance = new int[V+1];
		visited = new boolean[V+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.to;
			int weight = node.weight;
			
			for (int i = 0; i < adjList[now].size(); i++) {//현재 노드에서 갈수있는거 다 비용 체크
				Node next = adjList[now].get(i);
				int nextNode = next.to;
				int nextWeight = weight + next.weight;
				if(nextWeight < distance[nextNode]) {//기존에 있는 노드 비용값보다 더 적으면 업데이트
					distance[nextNode] = nextWeight;
					pq.add(new Node(nextNode, nextWeight));
				}
				
			}
		}
		for (int i = 1; i <= V; i++) {
			if(distance[i] != Integer.MAX_VALUE) {
				System.out.println(distance[i]);
			}else {
				System.out.println("INF");
			}
		}
		
	}

}
