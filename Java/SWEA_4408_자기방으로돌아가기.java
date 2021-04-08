package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4408_자기방으로돌아가기 {

	static int arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[401];
//			room = new ArrayList<Room>();
//			q = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = (Integer.parseInt(st.nextToken())-1)/2;
				int to = (Integer.parseInt(st.nextToken())-1)/2;
				//room.add(new Room(from,to));
				if(from > to) {
					int temp = from;
					from = to;
					to = temp;
				}
				for (int j = from; j <= to; j++) {
					arr[j]++;
				}
			}
			int ans = 0;
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] > ans) {
					ans = arr[i];
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}// end of test case
		System.out.print(sb);

	}

}
