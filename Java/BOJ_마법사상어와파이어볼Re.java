package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_마법사상어와파이어볼Re {
	static class Fireball{
		int y,x,m,s,d; // y x 질량 속도 방향
		int mergeCnt = 1; // 몇개합쳐졌는지
		boolean allSameDir = true; // 모두 짝수이거나 모두 홀수일때
		
		public Fireball(int y, int x, int m, int s, int d) {
			super();
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		public void move() {
			this.y = (this.y + (dy[d]*s)%N + N)%N;
			this.x = (this.x + (dx[d]*s)%N + N)%N;
		}
		
		public void merge(Fireball other) {
			this.m += other.m;
			this.s += other.s;
			mergeCnt++;
			if(allSameDir && (this.d%2 != other.d%2)) {
				allSameDir = false; // 한번이라도 달라지면 다른것
			}
		}
		@Override
		public String toString() {
			return "Fireball [y=" + y + ", x=" + x + ", m=" + m + ", s=" + s + ", d=" + d + ", mergeCnt=" + mergeCnt
					+ ", allSameDir=" + allSameDir + "]";
		}

	}
	static int N, M, K;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static Queue<Fireball> q;
	static Fireball[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		N = Integer.parseInt(init[0]); // 맵 크기
		M = Integer.parseInt(init[1]); // 파이어볼 개수
		K = Integer.parseInt(init[2]); // 명령어 횟수
		
		map = new Fireball[N][N];
		
		q = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Fireball fb = new Fireball(y, x, m, s, d);
			q.offer(fb);
		}
		
		while(!q.isEmpty()) {
			if(K==0) break;
			int size = q.size();
			while(size-- > 0) {
				Fireball now = q.poll();
				//파이어볼 이동
				now.move();
				if(map[now.y][now.x] == null) {
					map[now.y][now.x] = now;
				}else {
					map[now.y][now.x].merge(now);//누적해서 합쳐주기
				}
			}//이동 끝
			//분리하기
			split();
			K--;
		}
		
		int ans=0;
		while(!q.isEmpty()) {
			ans += q.poll().m;
		}
		System.out.println(ans);
	}
	
	private static void split() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != null) {
					//합치기
					Fireball fb = map[i][j];		
					map[i][j] = null; //원래 좌표는 지워주고
					if(fb.mergeCnt > 1) {
						int newM = fb.m/5;
						int newS = fb.s/fb.mergeCnt;
						if(newM > 0) {
							if(fb.allSameDir) {
								for (int d = 0; d < 4; d++) {
									q.add(new Fireball(fb.y, fb.x, newM, newS, d*2));
								}
							}else {
								for (int d = 0; d < 4; d++) {
									q.add(new Fireball(fb.y, fb.x, newM, newS, d*2+1));
								}
							}
							
						}
					}else {
						q.add(fb); //혼자 있는것도 큐에 넣어주기 
					}
				}
			}
		}
		
	}
}
