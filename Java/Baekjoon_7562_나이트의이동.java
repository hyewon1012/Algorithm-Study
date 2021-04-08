package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Info {
	int y;
	int x;
	Info(int y, int x){
		this.y = y;
		this.x = x;
	}
}

public class Baekjoon_7562_나이트의이동 {

/**
 * 나이트가 최소 몇번만에 도착점에 이동할수있는지
 * */
	static int[] dy = {2,1,-1,-2,-2,-1,1,2}; //오른쪽위부터 시계방향
	static int[] dx = {1,2,2,1,-1,-2,-2,-1}; //오른쪽위부터 시계방향
	static int N;
	static int[][] visited;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			int startY = 0, startX = 0, endY=0, endX=0;
			
			N = Integer.parseInt(br.readLine());
			String[] start = br.readLine().split(" ");
			startY = Integer.parseInt(start[0]);
			startX = Integer.parseInt(start[1]);
			String[] end = br.readLine().split(" ");
			endY = Integer.parseInt(end[0]);
			endX = Integer.parseInt(end[1]);
			
			visited = new int[N][N];
			
			answer = move(startY, startX, endY, endX);
			sb.append(answer).append("\n");
			
		}// end of test case
		System.out.print(sb);
	}//end of main
	
	//BFS로 넓어질때마다 이전값+1. BFS라 최소값 찾기 안해도됨
	private static int move(int startY, int startX, int endY, int endX) {
		int answer = 0;
		Queue<Info> q = new LinkedList<>();
		q.add(new Info(startY, startX));

		while(!q.isEmpty()) {
			Info now = q.poll();
			if(now.y == endY && now.x == endX) {
				answer = visited[now.y][now.x];
				break;
			}
			for (int i = 0; i < 8; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if(ny >= 0 && ny < N && nx >= 0 && nx < N && visited[ny][nx] == 0) {
					visited[ny][nx] = visited[now.y][now.x]+1;
					q.add(new Info(ny,nx));
				}
			}
			
		}
		return answer;
	}

}//end of class
