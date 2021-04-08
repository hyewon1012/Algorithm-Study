package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact {
	
	static List<Integer>[] adjList;
	static int[] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = 10;
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int length = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[101];
			distance = new int[101];
			Arrays.fill(distance, -1);
			for (int i = 1; i < 101; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < length/2; i++) {	
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
			}
			//BFS
			int ans = byBFS(start);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}//end of test case
		System.out.print(sb);

	}

	private static int byBFS(int start) {
		
		distance[start] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		while(!q.isEmpty()) {
			int now = q.poll();
			//같은 step만큼 돌림
			for (int i = 0; i < adjList[now].size(); i++) {
				int next = adjList[now].get(i);
				if(distance[next] == -1) {
					distance[next] = distance[now]+1;
					q.offer(next);
				}
			}
			
		}
		
		int maxDistance = distance[0];
		int ans = 0;
		for (int i = 1; i < 101; i++) {
			if(maxDistance <= distance[i]) {
				maxDistance = distance[i];
				ans = i;
			}
		}
		return ans;
	}

}
