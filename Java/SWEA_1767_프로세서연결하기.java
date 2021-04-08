package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;


class Core{
	int y;
	int x;
	public Core(int y, int x) {
		this.y = y;
		this.x = x;
		
	}
}
/**
 *  조합적문제
 *  1. 가장자리 코어 배제
 *  2. 부분집합 : 코어중에 몇개를 선택해서(5개중에 한개, 5개중에 두개 ,... 5개 전부) 전선을 연결했을때 전선길이의 합이 최소가될까
 *  -> 코어 선택 : 상하좌우 놓을수있는지 판단
 *  -> 코어선택안함
 * */
public class SWEA_1767_프로세서연결하기 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Core> cores;
	static int max;
	static int min;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int totalcnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<Core>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalcnt = 0;
			
			for (int i = 0; i < N; i++) {
				String[] s = br.readLine().split(" ");
				//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if((i==0 || i == N-1 || j==0 || j==N-1)) continue;
					if(map[i][j] == 1) {
						cores.add(new Core(i, j));
						totalcnt++;
						//cores.add(new int[] {i,j})
					}
						
					
				}
			}
			go(0,0);
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}// end of test case
		System.out.print(sb);
	} // end of main
	
	
	private static void go(int cnt, int idx) {
		if(idx == totalcnt) {
			int res = getLength(); // 놓아진 전선 길이 구하기
			if(max < cnt) {
				max = cnt;
				min = res;
			}else if(max == cnt) { // 연결한 코어 최대수가 지금 조합된 코어갯수랑 같음.
				if(res < min) { //지금 연결한 전선길이가 기존의 min보다 작으면 업데이트
					min = res;
				}
			}
			return;
		}
		//코어 선택 전선(4방향) 놓아보기
		int y = cores.get(idx).y;
		int x = cores.get(idx).x;
		for (int k = 0; k < 4; k++) {
			if(isAvailable(y, x, k)) {
				// 전선 놓기
				setStatus(y, x, k, 2);
				// 다음 코어로 넘어가기
				go(cnt+1, idx+1);
				//다음방향에 놓기위해서 놓았던 전선을 되돌려 놓자.
				setStatus(y, x, k, 0);
			}
		}
		//코어 비선택 전선 놓아보기
		go(cnt, idx+1);
		
		
	}
	
	private static int getLength() {
		int lCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 2) lCnt++;
			}
		}
		return lCnt;
	}


	private static void setStatus(int y, int x, int d, int s) {
		int ny = y, nx = x;
		while(true) {
			ny += dy[d];
			nx += dx[d];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N) break;
			map[ny][nx] = s;
		}
	}
	
	//끝까지 갈수있으면 true 아니면 false
	private static boolean isAvailable(int y, int x, int d) {
		int ny = y, nx = x;
		while(true) {
			ny += dy[d];
			nx += dx[d];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N) break;
			if(map[ny][nx] >= 1) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void dfs(int idx) {
		if(idx == cores.size()) return;
		
		
		int y = cores.get(idx).y;
		int x = cores.get(idx).x;
		for (int k = 0; k < 4; k++) {
			if(isConnet(y, x, k)) {
				
				
				visited[y][x] = true;
				int ny,nx;
				while(true) {
					ny = y + dy[k];
					nx = x + dx[k];
					if(ny < 0 || ny >= N || nx < 0 || nx >= N) break;
					visited[ny][nx] = true;
					y = ny;
					x = nx;
				}
				break;	
			}
		}
		//System.out.println(Arrays.deepToString(visited));
		dfs(idx+1);
		
	}

	private static boolean isConnet(int y, int x, int k) {
	
		while(true) {
			int ny = y + dy[k];
			int nx = x + dx[k];
			if(ny < 0 || ny >= N || nx < 0 || nx >= N) break;
			if(map[ny][nx] == 1) {
				return false;
			}
			y = ny;
			x = nx;
		}
		return true;
	}
}
