package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Home{
	int y;
	int x;
	public Home(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
class Chiken{
	int y;
	int x;
	public Chiken(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Baekjoon_15686_치킨배달 {
/**
 * 치킨 거리 : 집과 가장 가까운 치킨집 거리
 * 도시의 치킨거리가 가장 적은 치킨집 최대 M개 고르기.
 * 도시의 치킨 거리가 가장 적은 조합 찾기 주어진 치킨집중 M개 뽑는다
 * */
	static int[][] map;
	static int N;
	static int R;
	static ArrayList<Chiken> chiken;
	static ArrayList<Home> home;
	static ArrayList<Chiken> selected;
	static int[] flag;
	
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		N = Integer.parseInt(init[0]);
		R = Integer.parseInt(init[1]); // 치킨집 갯수
		chiken = new ArrayList<Chiken>(); // 치킨집 좌표
		map = new int[N][N];
		selected = new ArrayList<Chiken>(); // 선택한 치킨집 리스트
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
		//치킨집 R개
		//폐업시키지 않을 치킨집 0개 1개 2개 -> 운영 하는 치킨집개수 1,2,.. 최대 R개
		int dist = Integer.MAX_VALUE; //모든 최소 치킨거리
		flag = new int[chiken.size()];
		
		int cnt = 0;
		while(++cnt <= R) flag[chiken.size()-cnt]=1;
		do {
			int distSum = 0; // 조합마다 나오는 치킨거리
			for (Home h : home) {
				int singleDistance = Integer.MAX_VALUE;	
				for (int i=0; i< chiken.size(); i++) {
					if(flag[i]==1) {
						singleDistance = Math.min(singleDistance, Math.abs(h.y-chiken.get(i).y) + Math.abs(h.x-chiken.get(i).x));
					}
				}
				distSum += singleDistance;
			}
			dist = Math.min(dist, distSum);
		}while(np());
		
		System.out.println(dist);
		
	}
	
	public static boolean np() {
		int i = chiken.size()-1;
		while(i > 0 && flag[i-1] >= flag[i] ) --i;
		
		if(i==0) return false;
		
		int j = chiken.size()-1;
		while(flag[i-1] >= flag[j]) --j;
		swap(i-1, j);
		
		int k = chiken.size()-1;
		while(i < k) {
			swap(i++, k--);
		}
		return true;		
	}
	
	private static void swap(int i, int j) {
		int temp = flag[i];
		flag[i] = flag[j];
		flag[j] = temp;
	}

}
