package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2477_참외밭 {
/**
 * 북,남 방향일때 큰값, 작은값 기억
 * 동,서 방향일때 큰값, 작은값 기억
 * 가로max*세로max - 가로min*세로min
 * @throws IOException 
 * @throws NumberFormatException 
 * */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[6];
		int[] dir = new int[6];
		
		int heightMax = Integer.MIN_VALUE, widthMax = Integer.MIN_VALUE;
		int heightMin = Integer.MAX_VALUE, widthMin = Integer.MAX_VALUE;
		
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			dir[i] = d;
			arr[i] = val;
			if(d== 1 || d== 2) {
				widthMax = Math.max(widthMax, val);
			} 
			else{
				heightMax = Math.max(heightMax, val);
			}
		}
		for (int i = 0; i < 6; i++) {
			//꺽이는지점 
			//동서남북 1234
			int beforeDir = (i+5)%6;
			int nextDir = (i+1)%6;
			if(dir[beforeDir] == dir[nextDir]) {
				if(dir[i] == 1 || dir[i] == 2) {
					widthMin = arr[i];
				}else {
					heightMin = arr[i];
				}
			}
			
		}
		
		int ans = N*(heightMax*widthMax - heightMin*widthMin);
		System.out.print(ans);

	}

}
