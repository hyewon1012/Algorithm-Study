package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {
	static int N;
	static int[] people;
	static int[] areaA;
	static int[] areaB;
	static boolean[] isSelected;
	static List<Integer>[] adjList;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		adjList = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int adjCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjCnt; j++) {
				adjList[i+1].add(Integer.parseInt(st.nextToken()));
			}
		}
		ans = Integer.MAX_VALUE;
		solve(1<<N);
		ans = ans == Integer.MAX_VALUE ? -1 : ans;
		System.out.println(ans);
		
	}
	
	private static void solve(int Nbit) {
		List<Integer> groupA = new ArrayList<Integer>();
		List<Integer> groupB = new ArrayList<Integer>();
		for (int i = 0; i < Nbit; i++) {
			for (int j = 0; j < N; j++) {
				if((i & (1<<j)) > 0) groupA.add(j+1);
				else {
					groupB.add(j+1);
				}
			}
			
			if(groupA.size() > 0 && groupB.size() > 0) {
				//나눈 그룹들 각각 내부에서 연결되있는지 체크
				if(isConnect(groupA) && isConnect(groupB)) {
					ans = Math.min(ans, diffCount(groupA, groupB));
				}
			}

			groupA.clear();
			groupB.clear();
		}
		
	}

	private static int diffCount(List<Integer> groupA, List<Integer> groupB) {
		int sumA=0, sumB=0;
		for (int i = 0; i < groupA.size(); i++) {
			sumA+=people[groupA.get(i)-1];
		}
		for (int i = 0; i < groupB.size(); i++) {
			sumB+=people[groupB.get(i)-1];
		}
		return Math.abs(sumA-sumB);
	}
	
	private static boolean isConnect(List<Integer> group) {
		if(group.size() == 1) return true;
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];

		q.add(group.get(0));
		visited[group.get(0)]= true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int now = q.poll();
				for (int i = 0; i < adjList[now].size(); i++) {
					int next = adjList[now].get(i);
					
					if(!visited[next] && isNext(next, group)) {
						visited[next] = true;
						q.add(next);
					}
				}
			}
		}

		//vistied 조사
		int visitCnt = 0;
		for (int i = 0; i <= N; i++) {
			if(visited[i]) visitCnt++;
		}
		return visitCnt == group.size() ? true : false;
	}
	
	//다음 방문 노드가 group에 있는 원소인지 확인
	private static boolean isNext(int next, List<Integer> group) {
		boolean flag = false;
		for (int i = 0; i < group.size(); i++) {
			if(group.get(i) == next) {
				flag = true;
			}
		}
		return flag;
	}


}
