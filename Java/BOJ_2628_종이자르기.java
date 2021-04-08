package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2628_종이자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] init = br.readLine().split(" ");
		
		int width = Integer.parseInt(init[0]); // 가로
		int height = Integer.parseInt(init[1]); // 세로
		
		int cnt = Integer.parseInt(br.readLine());
		
		List<Integer> widthLength = new ArrayList<Integer>();
		List<Integer> heightLength = new ArrayList<Integer>();
		
		//시작점 끝점 초기화
		widthLength.add(0);
		widthLength.add(width);
		
		heightLength.add(0);
		heightLength.add(height);
		
		int l=0; // 가로 점선 개수
		for (int i = 0; i < cnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()); // 0이면 가로 1이면 세로
			int order = Integer.parseInt(st.nextToken());
			if(n==0) {
				heightLength.add(order);
				l++;
			}else {
				widthLength.add(order);
				
			}
		}
		Collections.sort(widthLength);
		Collections.sort(heightLength);
		
		int maxWidth = widthLength.get(0);
		int maxHeight = heightLength.get(0);
		
		for (int i = 1; i < heightLength.size(); i++) {
			maxHeight = Math.max(maxHeight, heightLength.get(i)-heightLength.get(i-1));
		}
		for (int i = 1; i < widthLength.size(); i++) {
			maxWidth = Math.max(maxWidth, widthLength.get(i)-widthLength.get(i-1));
		}
		System.out.println(maxHeight*maxWidth);

	}

}
