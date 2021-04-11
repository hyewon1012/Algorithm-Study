package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1251_하나로 {
	
	static int N;
	static int[] pick;
	static List<Node> graph;
	static List<Integer[]> edgeList;
	static class Node implements Comparable<Node>{
		int from,to;
		double cost;

		public Node(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}

	}
	static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new ArrayList<Node>();
			edgeList = new ArrayList<Integer[]>();
			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}
			List<Integer> x = new ArrayList<Integer>();
			List<Integer> y = new ArrayList<Integer>();
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					if(i == 0) {
						x.add(Integer.parseInt(st.nextToken()));
					}else {
						y.add(Integer.parseInt(st.nextToken()));
					}
				}
			}
			double e = Double.parseDouble(br.readLine());
			
			pick = new int[2];
			
			makeEdge(0,0);// 연결지을 노드 리스트 생성
			
			settingGraph(x, y, e); // 그래프 셋팅
			
			Collections.sort(graph);
			double ans = 0;
			for (int i = 0; i < graph.size(); i++) {
				Node now = graph.get(i);
				if(find(now.from) != find(now.to)) {
					union(now.from, now.to);
					ans += now.cost;
				}
			}
			
			sb.append("#").append(t).append(" ").append(Math.round(ans)).append("\n");
		}
		System.out.println(sb);
		
	} // end of main
	
	private static void settingGraph(List<Integer> x, List<Integer> y, double e) {
		
		for (int i = 0; i < edgeList.size(); i++) {
			Integer[] edge = edgeList.get(i);
			int from = edge[0];
			int to = edge[1];
			
			double distance = Math.pow(x.get(to)-x.get(from), 2) + Math.pow(y.get(to)-y.get(from), 2);
			double cost = e*distance;
			
			graph.add(new Node(from, to, cost));
		}
	}
	
	private static void makeEdge(int cnt, int start) {
		if(cnt == 2) {
			Integer[] temp = new Integer[2];
			for (int i = 0; i < 2; i++) {
				temp[i] = pick[i];
			}
			edgeList.add(temp);
			return;
		}
		for (int i = start; i < N; i++) {
			pick[cnt] = i; 
			makeEdge(cnt+1, i+1);
		}
	}
	
	private static int find(int v) {
		if(v == parents[v]) {
			return v;
		}
		return parents[v] = find(parents[v]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}
	}
}

