package com.ssafy.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Store{
	int y;
	int x;
	int dir;
	public Store(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}
class Start{
	int y;
	int x;
	int dir;
	public Start(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}
//북 남 -> (왼쪽으로부터의 거리)
//서 동 -> (위쪽으로부터의 거리)
public class Baekjoon_2564_경비원 {
	static int R,C,cnt;
	static int map[][];
	static ArrayList<Store> stores;
	static ArrayList<Start> home;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		C = Integer.parseInt(init[0]);
		R = Integer.parseInt(init[1]);
		map = new int[R+1][C+1];
		
		cnt = Integer.parseInt(br.readLine());
		stores = new ArrayList<Store>();
		home = new ArrayList<Start>();
		for (int i = 0; i < cnt; i++) {
			String[] s = br.readLine().split(" ");
			int dir = Integer.parseInt(s[0]);
			int dist = Integer.parseInt(s[1]);
			switch (dir) {
			case 1:
				stores.add(new Store(0,dist,dir));
				break;
			case 2:
				stores.add(new Store(R,dist,dir));
				break;
			case 3:
				stores.add(new Store(dist,0,dir));
				break;
			case 4://동쪽
				stores.add(new Store(dist,C,dir));
				break;
			
			}
		}
		String[] homeInfo = br.readLine().split(" ");
		int startDir = Integer.parseInt(homeInfo[0]);
		int startDist = Integer.parseInt(homeInfo[1]);
		
		switch (startDir) {
		case 1:
			home.add(new Start(0,startDist,startDir));
			break;
		case 2:
			home.add(new Start(R,startDist,startDir));
			break;
		case 3:
			home.add(new Start(startDist,0,startDir));
			break;
		case 4://동쪽
			home.add(new Start(startDist,C,startDir));
			break;
		}
		//solve
		int result = 0;
		for (int i = 0; i < stores.size(); i++) {
			result += getDistance(i);
		}
		System.out.println(result);
		
	}
	//시작점 ~ 각 상점사이 최단거리합
	private static int getDistance(int storeIdx) {
		int dist = 0;
		int rowSize = map.length-1;
		int colSize = map[0].length-1;
		int length = 0;
		
		int diff = Math.abs(home.get(0).dir - stores.get(storeIdx).dir);
		int check = home.get(0).dir + stores.get(storeIdx).dir;
		
		if(diff == 0) {
			if(home.get(0).dir == 1 || home.get(0).dir == 2) {
				dist += Math.abs(home.get(0).x - stores.get(storeIdx).x);
			}else {
				dist += Math.abs(home.get(0).y - stores.get(storeIdx).y);
			}
			 
		}else if(check == 3 || check == 7){//정반대방향
			//반시계
			length = (colSize-home.get(0).x) + rowSize + (colSize-stores.get(storeIdx).x);
			//시계
			length = Math.min(length, home.get(0).x + rowSize + stores.get(storeIdx).x);
			dist += length;
		}else {//인접한방향
			//반시계
			length = (colSize-home.get(0).x) + (rowSize - stores.get(storeIdx).y);
			//시계
			length = Math.min(length, home.get(0).x + (rowSize - stores.get(storeIdx).y));
			dist += length;
			
		}
		return dist;
	}

}
