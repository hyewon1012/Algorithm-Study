package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_마법사상어와파이어볼_20056 {
	static int N,M,K;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static Queue<Fireball> queue;
	static int[] deltaSetA = {0,2,4,6}, deltaSetB = {1,3,5,7};
	static Fireball[][] map;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		N = Integer.parseInt(init[0]); // 맵 크기
		M = Integer.parseInt(init[1]); // 파이어볼 개수
		K = Integer.parseInt(init[2]); // 명령어 횟수
		
		queue = new LinkedList<>();//fireball 정보
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			queue.offer(new Fireball(y, x, m, s, d));
		}
		//System.out.println(queue);
		while(!queue.isEmpty()) {
			//명령이 남아있지 않으면 끝
			if(K == 0) {
				break;
			}
			
			//단계별로 fireball 이동시키기
			//이동 + merge
			int size = queue.size();
			while(size-- > 0) {
				Fireball front = queue.poll();
				front.move();
				//이동 후 가는 지점이 비어있으면 현재 원소 자리잡기
				if(map[front.y][front.x] == null) {
					map[front.y][front.x] = front;
				}else { // 다른 파이어볼이 있으면 합치기
					map[front.y][front.x].merge(front);
				}
			}//이동 완료
			
			//merge된것 다시 분리하기
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if(map[i][j] != null) {
						Fireball fb = map[i][j];
						map[i][j] = null;
						if(fb.mergeCnt > 1) {
							int newMess = fb.m/5;
							if(newMess == 0) continue;
							int newSpeed = fb.s/fb.mergeCnt;
							int [] deltaSet = fb.allSameDir ? deltaSetA : deltaSetB;
							for (int d = 0; d < deltaSet.length; d++) {
								queue.offer(new Fireball(fb.y, fb.x, newMess, newSpeed, deltaSet[d]));
							}
						}else {//합쳐지지 않은건 그냥 가기
							queue.offer(fb);
						}
					}
				}
			}//merge 처리 완료
			K--;
		}
		
		int ans = 0;
		while(!queue.isEmpty()) {
			ans += queue.poll().m;
		}
		System.out.println(ans);
		
		
	} // end of main
	
	//모든 파이어볼 명령어 한번씩 수행
//	private static void move() {
//		
//		for (Fireball ele : pos) {
//			//음수나올때
////			int ny = ele.y + (dy[ele.d]*(ele.s%N))%N;
////			int nx = ele.x + (dx[ele.d]*(ele.s%N))%N;
//			int ny = (ele.y + dy[ele.d]*(ele.s%N))%N;
//			int nx = (ele.x + dx[ele.d]*(ele.s%N))%N;
////			if(ny >= N) {
////				ny = ny-N;
////			}else if(ny < 0) {
////				ny = ny + N;
////			}
////			if(nx >= N) {
////				nx = nx-N;
////			}else if(nx < 0) {
////				nx = nx + N;
////			}
//			ele.y = ny;
//			ele.x = nx;
//			map[ny][nx].add(ele);
//		}
//		
//		//같은좌표에 있는 파이어볼 번호 -> 어덯게 찾아주지?
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if(map[i][j].size() > 1) {
//					//합치기
//					int s_sum=0, m_sum = 0;
//					boolean even = true;
//					boolean odd = true;
//					for (Fireball fb : map[i][j]) {
//						m_sum += fb.m;
//						s_sum += fb.s;
//						//방향 모두 홀수 or 모두 짝수 - > 0246 아니면 1357
//						if(fb.d % 2 == 0) odd = false;
//						if(fb.d % 2 == 1) even = false;
//						
//					}
//					int new_m = m_sum / 5;
//					int new_s = s_sum / map[i][j].size();
//					map[i][j].clear();//합쳐진얘들은 원래있던 좌표에서 다 사라지게
//					if(new_m > 0) {
//						//추가
//						for (int k = 0; k < 4; k++) {
//							if(odd || even) {
//								map[i][j].add(new Fireball(i, j, new_m, new_s, k*2));
//							}else {
//								map[i][j].add(new Fireball(i, j, new_m, new_s, k*2+1));
//							}
//						}
//						
//					}
//					
//				}
//				
//			}
//		}
//		
//	}
	
	static class Fireball{
		int y,x,m,s,d; // y x 질량 속력 방향
		int mergeCnt=1; // 몇개합쳐졌는지
		boolean allSameDir = true; // 합쳐질때 모두 짝수 또는 모두 홀수
		
		public Fireball(int y, int x, int m, int s, int d) {
			this.y=y;
			this.x=x;
			this.m=m;
			this.s=s;
			this.d=d;
		}
		
		public void move() {
			//+N : % 연산 결과가 음수일때를 고려해서
			this.y = (this.y + (dy[d]*s)%N + N)%N; // 음수가 나오면 처리를 못해준다. -> N 더해주면됨
			this.x = (this.x + (dx[d]*s)%N + N)%N;
		}
		public void merge(Fireball other) {
			// 중량 : 더해진다
			this.m += other.m;
			// 속도 : 더해진다
			this.s += other.s;
			//mergeCnt
			mergeCnt++;
			//방향 - 중간에 한번이라도 달라지면 다른것
			if(allSameDir && (this.d%2 != other.d%2)) {
				allSameDir = false;
			}
		}
		
		@Override
		public String toString() {
			return "Fireball [y=" + y + ", x=" + x + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
		
	}

}

