package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
	static int R,C,T;
	static Dust[][] map;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static class Dust{
		int y,x,cnt;

		public Dust(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
		public int getCnt() {
			return cnt;
		}

		public void setCnt(int cnt) {
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Dust [y=" + y + ", x=" + x + ", cnt=" + cnt + "]";
		}
		
	}
	static int cy1=-1,cy2=0; // 공기청정기 바닥 좌표
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new Dust[R][C];
		
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				int dustCnt = Integer.parseInt(st.nextToken());
				map[i][j] = new Dust(i,j,dustCnt);
//				if(dustCnt > 0) {
//					q.add(map[i][j]);
//				}
				if(dustCnt == -1) {
					if(cy1 == -1) {
						cy1 = i;
					}else {
						cy2 = i;
					}
					
				}
			}
		}
		solve();
	}
	public static void solve() {
		int ans = 0;
		for (int t = 0; t < T; t++) {
			//미세먼지 확산
			spread();
			//공기청정기 작동
			clean();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j].cnt > 0) {
					ans += map[i][j].cnt;
				}
				
			}
		}
		System.out.println(ans);
	}
	
	private static void clean() {
		//위쪽 반시계
		for (int i = cy1-2; i >= 0; i--) {//아래
			map[i+1][0].setCnt(map[i][0].cnt);
		}
		for (int i = 0; i < C-1; i++) {//왼
			map[0][i].setCnt(map[0][i+1].cnt);
		}
		for (int i = 0; i < cy1; i++) {// 위
			map[i][C-1].setCnt(map[i+1][C-1].cnt);
		}
		for (int i = C-2; i >= 0; i--) {//오른
			map[cy1][i+1].setCnt(map[cy1][i].cnt);
		}
		map[cy1][1].setCnt(0);
		
		//아래쪽 시계
		for (int i = cy2+1; i < R-1; i++) {// 위
			map[i][0].setCnt(map[i+1][0].cnt);
		}
		for (int i = 0; i < C-1; i++) {//왼
			map[R-1][i].setCnt(map[R-1][i+1].cnt);
		}
		for (int i = R-2; i >= cy2; i--) {//아래
			map[i+1][C-1].setCnt(map[i][C-1].cnt);
		}
		for (int i = C-2; i >= 0; i--) {//오른
			map[cy2][i+1].setCnt(map[cy2][i].cnt);
		}

		map[cy2][1].setCnt(0);		
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(map[i][j].cnt+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
	
	public static void spread() {
		Queue<Dust> q = new LinkedList<Dust>();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j].cnt > 0) {
					q.add(map[i][j]);
				}
			}
		}
		int[][] spreadTotal = new int[R][C];
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0){
				Dust now = q.poll();
				int spreadCnt = 0;
				if(now.cnt >= 5) {
					for (int d = 0; d < 4; d++) {
						int ny = now.y + dy[d];
						int nx = now.x + dx[d];
						if(ny >= R || ny < 0 || nx >= C || nx < 0) continue;
						//확산 먼저 시켜준다
						Dust nextDust = map[ny][nx];
						if(nextDust != null && nextDust.cnt != -1) {
							spreadCnt++;
							spreadTotal[ny][nx] += now.cnt/5; // 다음칸에 원래있던값 + 확산되는양
						}
					}
					int remain = now.cnt-(now.cnt/5)*spreadCnt; //미세먼지 누적값 업데이트 
					now.setCnt(remain); // 남은 미세먼지 양 업데이트
				}
				
			} // 확산 끝
			
			// 확산된값 업데이트시켜주기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					Dust now = map[i][j];
					if(now.cnt != -1) {
						now.setCnt(now.cnt+spreadTotal[i][j]);
					}
					
				}
			}
		}
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(map[i][j].cnt+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
}
