package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Enemy {
	int y;
	int x;
	public Enemy(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}
class Archer {
	int y;
	int x;

	public Archer(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}

class ShortestAndLeft implements Comparable<ShortestAndLeft>{
	int y;
	int x;
	int distance;
	
	public ShortestAndLeft(int y, int x, int distance) {
		this.y = y;
		this.x = x;
		this.distance = distance;
	}

	@Override
	public int compareTo(ShortestAndLeft o) {
		//거리 가까운것부터 (오름차순정렬)
		int dist = this.distance - o.distance;
		if(dist == 0) {
			//가장 왼쪽에 있는것(오름차순정렬)
			return this.x - o.x;
		}
		return dist;
	}
	
}

public class Baekjoon_17135_캐슬디펜스 {
/**
 * N*M N행 밑에는 모두 성
 * 1. 각 턴마다 궁수 적 한명 공격. 거리가 D이하인 적 중에서 가장 가까운 적. 가장 왼쪽에 있는 적 공격
 * 2. 적 아래로 한칸 이동
 * 3. 모든 적이 격자판에서 제외되면 게임끝
 * 궁수의 공격으로 제거할수있는 적의 최대 수.
 * 궁수 3명 배치 조합 -> 궁수 3명 배치 위치 인덱스 저장-> 최대값 찾기
 * */
	static int N,M,D;
	static int[][] map;
	static int[][] copyMap;
	static boolean[] visited;
	static ArrayList<Enemy> enemy;
	static ArrayList<Archer> archer;
	static int ans =Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		D = Integer.parseInt(info[2]);
		
		map = new int[N+1][M];
		copyMap = new int[N+1][M];
		visited = new boolean[M];
		enemy = new ArrayList<Enemy>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j]; // 초기 셋팅
				if(map[i][j] == 1) {
					enemy.add(new Enemy(i, j));
				}
			}
		}
		
		setArcher(0,0);
		solve();
		System.out.println(ans);

	}
	
	//궁수배치 -> 2차원배열 조합
	private static void setArcher(int cnt, int idx) {
		
		if(cnt == 3) {
			//구현
			archer = new ArrayList<Archer>();
			for (int i = 0; i < M; i++) {
				if(visited[i]) {
					archer.add(new Archer(N, i));
				}
			}
			solve();
			
			return;
		}
		for (int i = idx; i < M; i++) {
			if(visited[idx]) continue;
			visited[idx] = true;
			setArcher(cnt+1, idx+1);
			visited[idx] = false;
		}

	}
	
	private static void solve() {
		int kill = 0;
		int line = N;
		
		while(line > 0) {
			ArrayList<Enemy> killList = new ArrayList<Enemy>();
			PriorityQueue<ShortestAndLeft> pq = new PriorityQueue<ShortestAndLeft>();
			//궁수기준 적 탐색
			for (Archer arch : archer) {
				for (Enemy e : enemy) {
					int dist = Math.abs(arch.y-e.y) + Math.abs(arch.x-e.x);

					if(dist <= D) {
						//1. 가장가깝고 			
						//2. x좌표 가장왼쪽인것
						pq.add(new ShortestAndLeft(e.y, e.x, dist));
					}
				}
				
				if(!pq.isEmpty()) {
					ShortestAndLeft shortest = pq.poll();
					int y = shortest.y;
					int x = shortest.x;
					killList.add(new Enemy(y, x));
				}
			}
			//사격
			for (int i = 0; i < killList.size(); i++) {
				int y = killList.get(i).y;
				int x = killList.get(i).x;
				if(copyMap[y][x] == 1) {
					copyMap[y][x] = 0;
					kill += 1;
				}
				
			}
			//아래로 한칸 내리기
			for (int i = N-2; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					copyMap[i+1][j] = copyMap[i][j];
				}
			}
			for (int i = 0; i < M; i++) {
				copyMap[0][i] = 0;
			}
			ans = Math.max(ans, kill);
			line--;
		}
	}
	
}
