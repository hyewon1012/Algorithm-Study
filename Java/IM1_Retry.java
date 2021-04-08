package com.java.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class IM1_Retry {
	static int N, A, B;
	static List<Integer> tops;
	
	static List<Integer>  A_top; //1번 탑에 들어갈 블록들 무게 정보
	static List<Integer> B_top; //2번 탑에 들어갈 블록들 무게 정보
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			String[] info = br.readLine().split(" ");
			N = Integer.parseInt(info[0]); // 블록 총 개수
			A = Integer.parseInt(info[1]); //1번 탑 개수
			B = Integer.parseInt(info[2]); //2번 탑 개수
			tops = new ArrayList<Integer>();
			
			A_top = new ArrayList<Integer>();
			B_top = new ArrayList<Integer>();
			

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N; i++) {	
				tops.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(tops, Collections.reverseOrder());;
			
			ans = 0;
			
			int i=0;
			int j=0;
			
			while(i <= tops.size()-1) {
				if(A_top.size() < A) {
					A_top.add(tops.get(i++));	
				}
				if(B_top.size() < B) {
					B_top.add(tops.get(i++));
					
				}
			}
			int cnt=1;
			for (int k = 0; k < A_top.size(); k++) {
				ans += cnt*A_top.get(k);
				cnt++;
			}
			cnt = 1;
			for (int k = 0; k < B_top.size(); k++) {
				ans += cnt*B_top.get(k);
				cnt++;
			}
			
			
			//solve
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		} // end of test case
		System.out.print(sb);
	}

}
