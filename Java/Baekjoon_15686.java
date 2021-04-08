package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Baekjoon_15686 {
	
	static int[][] map;
	static int N;
	static int R;
	static ArrayList<Chiken> chiken;
	static ArrayList<Home> home;
	//static ArrayList<Chiken> selected;
	static int[] selected;
	static int[] flag;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		N = Integer.parseInt(init[0]);
		R = Integer.parseInt(init[1]); // 치킨집 갯수
		chiken = new ArrayList<Chiken>(); // 치킨집 좌표
		map = new int[N][N];
		//selected = new ArrayList<Chiken>(); // 선택한 치킨집 리스트
		
		home = new ArrayList<Home>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					home.add(new Home(i,j));
				}
				if(map[i][j] == 2) {
					chiken.add(new Chiken(i, j));
				}
			}
		}
		selected = new int[R];
		ans = Integer.MAX_VALUE;
		
		comb(0,0);
		
		System.out.println(ans);
		
	}
	static void comb(int cnt, int start) {
		if(start >= chiken.size()) return;
		if(cnt == R) {
			//구현
			int distSum = 0; // 조합마다 나오는 치킨거리
			for (Home h : home) {
				int singleDistance = Integer.MAX_VALUE;
				for (int i = 0; i < selected.length; i++) {
					int idx = selected[i];
					singleDistance = Math.min(singleDistance, Math.abs(h.y-chiken.get(idx).y) + Math.abs(h.x-chiken.get(idx).x));
				}
//				for (Chiken c : selected) {
//					singleDistance = Math.min(singleDistance, Math.abs(h.y-c.y) + Math.abs(h.x-c.x));
//				}
				distSum += singleDistance;
			}
			ans = Math.min(ans, distSum);
			return;
		}
		for (int i = start; i < chiken.size(); i++) {
			selected[cnt] = i;
			//selected.set(i, chiken.get(i));//중복체크?
			//selected.add(chiken.get(i));
			comb(cnt+1, start+1);
			//selected.remove(chiken.get(i));
		}
	}

}
