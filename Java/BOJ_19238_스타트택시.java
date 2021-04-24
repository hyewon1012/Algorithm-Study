package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시 {
	static int N,M,F;
	static int[][] map;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	static int startY,startX;
	
	static class Taxi implements Comparable<Taxi>{
		int y,x;
		int fuel;
		int customerId;
		public Taxi(int y, int x, int fuel, int customerId) {
			super();
			this.y = y;
			this.x = x;
			this.fuel = fuel;
			this.customerId = customerId;
		}
		@Override
		public int compareTo(Taxi o) {
			if(this.fuel == o.fuel) {
				if(this.y == o.y) {
					return Integer.compare(this.x, o.x);//오름차순
				}else {
					return Integer.compare(this.y, o.y);//오름차순
				}
			}
			return Integer.compare(o.fuel, this.fuel); // 내림차순
		}
		
		@Override
		public String toString() {
			return "Taxi [y=" + y + ", x=" + x + ", fuel=" + fuel + ", customerId=" + customerId + "]";
		}
		
	}
	
	static class Dest {
		int y,x;

		public Dest(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Dest [y=" + y + ", x=" + x + "]";
		}
	}
	static Dest[] dest;
	static PriorityQueue<Taxi> pq = new PriorityQueue<Taxi>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken()); // 초기 연료 양
		
		map = new int[N][N];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		startY = Integer.parseInt(st.nextToken()); // 택시 시작 위치
		startX = Integer.parseInt(st.nextToken()); // 택시 시작 위치
		
		dest = new Dest[M+2]; //2,3,... id값
		int id = 2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cy = Integer.parseInt(st.nextToken()); // 고객 출발 
			int cx = Integer.parseInt(st.nextToken()); // 고객 출발
			int ey = Integer.parseInt(st.nextToken()); // 고객 도착
			int ex = Integer.parseInt(st.nextToken()); // 고객 도착
			map[cy][cx] = id;
			dest[id] = new Dest(ey,ex);
			id++;
		}
		solve();
		
	}// end of main
	private static void solve() {
		boolean fail = false;
		for (int i = 0; i < M; i++) {	
			Taxi taxi = findPassenger(); //1. 고객 찾으러가기
			if(taxi == null) {//고객 못 찾으면실패
				fail = true; 
				break;
			}
			map[taxi.y][taxi.x] = 0; //2. 손님 태우면 지워주기
			
			taxi = toDest(taxi); //3. 고객 목적지로 데려다주기
			if(taxi == null) {//고객 못데려다주면 실패
				fail = true;
				break;
			}
		}
		System.out.println(fail ? -1 : F);
		
	}

	private static Taxi findPassenger() {
		//현재위치가 출발점이면
		if(map[startY][startX] > 1) return new Taxi(startY, startX, F, map[startY][startX]);
		
		PriorityQueue<Taxi> pq = new PriorityQueue<Taxi>();
		Queue<Taxi> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		
		Taxi taxi = new Taxi(startY, startX, F, 0);
		q.offer(taxi);
		visited[startY][startX] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) { // 고객만나면 중단하는게 아니라 계속 탐색해야함. 
				Taxi now = q.poll();
				int y = now.y;
				int x = now.x;
				if(now.fuel == 0) {
					return null;
				}
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					if(ny >= N || ny < 1 || nx >= N || nx < 1) continue;
					if(map[ny][nx] != 1 && !visited[ny][nx]) {
						if(map[ny][nx] > 1) { // 손님이면 pq에 넣어줌
							pq.add(new Taxi(ny,nx,now.fuel-1, map[ny][nx]));
						}else { // 가는길이면 연료 1 차감
							q.add(new Taxi(ny,nx,now.fuel-1, 0));
						}
						visited[ny][nx] = true;
						
					}
				}
			}//고객 모두 찾아서 pq에 다 넣어주는거 긑
			if(pq.size() > 0) return pq.poll();
			
		}
		return null;
	}
	
	private static Taxi toDest(Taxi taxi) {
		//목적지까지 가기 전 연료량
		int baseFuel = taxi.fuel;
		Dest target = dest[taxi.customerId];
		Queue<Taxi> q = new LinkedList<>();
		q.offer(taxi);
		
		boolean[][] visited = new boolean[N][N];
		visited[taxi.y][taxi.x] = true;
		
		while(!q.isEmpty()) {
			Taxi now = q.poll();
			if(now.fuel == 0) return null;
			if(now.y == target.y && now.x == target.x) {
				Taxi newTaxi = new Taxi(now.y, now.x, now.fuel, now.customerId);
				startY = now.y;
				startX = now.x;
				//연료 계산
				F = newTaxi.fuel + (baseFuel - newTaxi.fuel)*2;
				return newTaxi;
			}
			for (int d = 0; d < 4; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if(ny >= N || ny < 1 || nx >= N || nx < 1) continue;
				if(map[ny][nx] != 1 && !visited[ny][nx]) {
					q.add(new Taxi(ny,nx,now.fuel-1, now.customerId));
					visited[ny][nx] = true;
				}
			}
		}
		
		return null;
	}
	

}
