package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_10158_개미2 {
	
	static int[] dx = {1,-1,-1, -1};
	static int[] dy = {1,1,-1 ,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] info = br.readLine().split(" ");
		int W = Integer.parseInt(info[0]);
		int H = Integer.parseInt(info[1]);
		
		String[] pos = br.readLine().split(" ");
		int c = Integer.parseInt(pos[0]);
		int r = Integer.parseInt(pos[1]);
		
		int t = Integer.parseInt(br.readLine());
		
		int dr = 1; //개미가 이동할 양, 벽에 부딪히면 -1곱하기
		int dc = 1;
		while(t > 0) {
			if(r == 0 || r == H) {
				dr *= -1;
			}
			if(c == 0 || c == W) {
				dc *= -1;
			}
			int lenR = H - r; // r위치에서 위아래 경계까지의 거리
			if(dr == -1){
				lenR = r;
			}
			int lenC = W - c; // c위치에서 위아래 경계까지의 거리
			if(dc == -1) {
				lenC = c;
			}
			int min = Math.min(Math.min(lenR, lenC), t);
			r = r + dr*min;
			c = c + dc*min;
			t -= min;
		}
		System.out.println(c + " " + r);

	}

}
