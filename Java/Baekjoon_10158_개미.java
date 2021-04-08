package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_10158_개미 {
	
	static int[] dx = {1,-1,-1, -1};
	static int[] dy = {1,1,-1 ,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] info = br.readLine().split(" ");
		int W = Integer.parseInt(info[0]);
		int H = Integer.parseInt(info[1]);
		
		String[] pos = br.readLine().split(" ");
		int x = Integer.parseInt(pos[0]);
		int y = Integer.parseInt(pos[1]);
		
		int t = Integer.parseInt(br.readLine());
		
		//t초 후 좌표 계산
/*		int ny = 0;
		int nx = 0;
		int d = 0;
		for (int i = 0; i < t; i++) {
			ny = y + dy[d];
			nx = x + dx[d];
			
			if(ny == H || ny == 0 || nx == W || nx == 0) {
				d = (d+1) % 4;
			}
			y = ny;
			x = nx;

			//System.out.println(x + " " + y);
			
		}
*/
		int xCnt = (x+t)/W; //t초동안 x기준 W를 몇번 움직였는지
		int yCnt = (y+t)/H; //t초동안 y기준 W를 몇번 움직였는지
		int p,q;
		
		//x좌표
		if(xCnt % 2 == 0) { // 왼 -> 오
			p = (x+t) % W;
		}else { // 왼 <- 오
			p = W - ((x+t) % W);
		}
		//y좌표
		if(yCnt % 2 == 0) { // 위로올라감
			q = (y+t) % H;
		}else { //아래로 내려감
			q = H - ((y+t) % H);
		}
		sb.append(p).append(" ").append(q);
		System.out.println(sb);
	}

}
